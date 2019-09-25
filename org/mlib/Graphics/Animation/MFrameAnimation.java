package org.mlib.Graphics.Animation;

import org.mlib.Graphics.Tools.MRect;
import org.mlib.Graphics.Tools.MVec2;
import org.mlib.System.Time.MTimer;
import java.util.Vector;

public class MFrameAnimation implements MAnimation {
    Vector<MRect> frames;
    int currentFrameIndex;
    long duration;
    MAnimationListener listener;

    public MFrameAnimation() {
        this(new Vector<>(), 0);
    }

    public MFrameAnimation(MRect pattern, MVec2 offset, int length, long duration) {
        setFrames(pattern, offset, length);
        setDuration(duration);

        this.listener = new MAnimationListener();
    }

    public MFrameAnimation(Vector<MRect> frames, long duration) {
        setFrames(frames);
        setDuration(duration);

        this.listener = new MAnimationListener();
    }

    public void setFrames(MRect pattern, MVec2 offset, int length) {
        this.frames = new Vector<>();

        for (int i = 0; i < length; ++i)
            frames.add(
                    new MRect(
                            pattern.left + offset.x * i,
                            pattern.top + offset.y * i,
                            pattern.right,
                            pattern.bottom
                    )
            );
    }

    public void setFrames(Vector<MRect> frames) {
        this.frames = frames;
        this.currentFrameIndex = 0;
    }

    public void setCurrentFrameIndex(int currentFrameIndex) {
        this.currentFrameIndex = currentFrameIndex;
    }

    public void setDuration(long mills) {
        this.duration = mills;
    }

    public Vector<MRect> getFrames() {
        return this.frames;
    }

    public MAnimationListener getListener() {
        return this.listener;
    }

    @Override
    public void activate() {
        if (this.frames == null)
            return;

        new Thread(
                () -> {
                    Vector<Float> data = new Vector<>();

                    for (MRect frame : frames) {
                        data.add(frame.left);
                        data.add(frame.top);
                        data.add(frame.right);
                        data.add(frame.bottom);
                        data.add(frame.center.x);
                        data.add(frame.center.y);

                        listener.onStep(data);

                        data.clear();

                        MTimer.waiting(duration);
                    }

                    listener.onFinish();
                }
        ).start();
    }
}