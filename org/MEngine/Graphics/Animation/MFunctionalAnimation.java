package org.MEngine.Graphics.Animation;

import android.util.Pair;

import org.MEngine.Graphics.Tools.MVec2;
import org.MEngine.System.Time.MTimer;
import org.MEngine.Tools.Graph.MFunctionGraph;
import java.util.Vector;

public class MFunctionalAnimation implements MAnimation {
    private Vector<MFunctionGraph> timingFunctions;
    private float duration;
    private Vector<Vector<Float>> intervals;
    private Vector<Pair<Float, Float>> ranges;
    private MAnimationListener listener;

    public MFunctionalAnimation() {
        this(new Vector<MFunctionGraph>(), 0);
    }

    public MFunctionalAnimation(Vector<MFunctionGraph> timingFunctions, float duration) {
        this.intervals = new Vector<>();
        this.ranges = new Vector<>();
        this.listener = new MAnimationListener();

        setAnimation(timingFunctions, duration);
    }

    public void setAnimation(Vector<MFunctionGraph> timingFunctions, float duration) {
        this.timingFunctions = timingFunctions;
        this.duration = duration;

        calculateTransition();
    }

    private void addInterval(Vector<Float> interval) {
        if (this.intervals == null)
            this.intervals = new Vector<>();

        this.intervals.add(interval);
    }

    public void setRanges(Vector<Pair<Float, Float>> ranges) {
        this.ranges = ranges;
    }

    public void setListener(MAnimationListener listener) {
        this.listener = listener;
    }

    public Vector<MFunctionGraph> getTimingFunctions() {
        return this.timingFunctions;
    }

    public float getDuration() {
        return this.duration;
    }

    public Vector<Vector<Float>> getIntervals() {
        return this.intervals;
    }

    public Vector<Pair<Float, Float>> getRanges() {
        return this.ranges;
    }

    public MAnimationListener getListener() {
        return this.listener;
    }

    @Override
    public void activate() {
        if (this.intervals == null)
            return;

        new Thread(
                () -> {
                    Vector<Float> frame = new Vector<>();

                    for (int i = 0; i < intervals.firstElement().size(); ++i) {
                        for (int j = 0; j < intervals.size(); ++j)
                            frame.add(ranges.get(j).first + intervals.get(j).get(i) * ranges.get(j).second);

                        if (!listener.onStep(frame))
                            break;

                        frame.clear();
                        MTimer.waiting(16);
                    }

                    listener.onFinish();
                }
        ).start();
    }

    private void calculateTransition() {
        final float frameTime = 0.016666f;
        float step = frameTime / this.duration;
        this.intervals.clear();

        for (MFunctionGraph timingFunction : getTimingFunctions())
            addInterval(
                    timingFunction.interpolation(
                            0f, 1f, step
                    )
            );
    }
}