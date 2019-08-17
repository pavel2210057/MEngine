package org.mlib.Graphics.Tools;

import android.graphics.Point;

public class MVec2 {
    public float x, y;

    public MVec2() {
        this(0, 0);
    }

    public MVec2(float xy) {
        this(xy, xy);
    }

    public MVec2(Point point) {
        this(point.x, point.y);
    }

    public MVec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean compare(MVec2 right) {
        return
                this.x == right.x &&
                this.y == right.y;
    }
}