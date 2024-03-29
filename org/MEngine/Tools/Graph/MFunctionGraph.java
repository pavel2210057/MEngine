package org.MEngine.Tools.Graph;

import android.util.Pair;
import org.MEngine.Graphics.Tools.MVec2;
import java.util.Arrays;
import java.util.Vector;

public class MFunctionGraph {
    private Vector<Pair<Float, Float>> graphData; //коеффициент, степень x

    public MFunctionGraph() {
        this(new Vector<>());
    }

    @SafeVarargs
    public MFunctionGraph(Pair<Float, Float> ...data) {
        this(new Vector<>(Arrays.asList(data)));
    }

    public MFunctionGraph(Vector<Pair<Float, Float>> data) {
        setEquation(data);
    }

    public void setEquation(Vector<Pair<Float, Float>> data) {
        this.graphData = data;
    }

    public float getValue(float abscissa) {
        float result = 0;

        for (Pair<Float, Float> value : this.graphData)
            result += value.first * Math.pow(abscissa, value.second);

        return result;
    }

    public Vector<Pair<Float, Float>> getData() {
        return this.graphData;
    }

    public Vector<Float> interpolation(float rangeBegin, float rangeEnd, float step) {
        Vector<Float> transition = new Vector<>();

        for (float i = rangeBegin; rangeBegin < rangeEnd ? i <= rangeEnd : i >= rangeEnd; i += step)
            transition.add(getValue(i));

        return transition;
    }

    public static MFunctionGraph fromVector(MVec2 vec2, boolean isNormalized) {
        if (!isNormalized)
            vec2 = vec2.getNormalized();

        return new MFunctionGraph(
                new Pair<>(vec2.x, 1f),
                new Pair<>(vec2.y, 1f)
        );
    }
}