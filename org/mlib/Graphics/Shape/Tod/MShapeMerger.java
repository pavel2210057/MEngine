package org.mlib.Graphics.Shape.Tod;

import android.util.Pair;
import java.util.HashMap;

public class MShapeMerger extends MShape {
    private HashMap<MShape, Pair<Integer, Integer>> drawBuffer; //shape -> (begin index, end index)

    public MShapeMerger() {
        this.drawBuffer = new HashMap<>();
    }

    public MShapeMerger(MShape[] shapes) {
        this.drawBuffer = new HashMap<>();
        merge(shapes);
    }

    public void merge(MShape[] shapes) {
        int index = 0;
        for (int i = 0; i < shapes.length; ++i) {
            MShape
                    current = shapes[i],
                    next = i == shapes.length - 1 ? null : shapes[i + 1];

            if (next != null && !current.compare(next))
                return;

            this.drawBuffer.put(
                    current,
                    new Pair<>(index, (index += current.getVertices().size()))
            );
        }

        for (MShape shape : this.drawBuffer.keySet())
            super.setVertices(shape.getVertices());

        super.setPrimitive(shapes[0].getPrimitive());
    }

    public Pair<Integer, Integer> getIndexes(MShape shape) {
        return this.drawBuffer.get(shape);
    }
}