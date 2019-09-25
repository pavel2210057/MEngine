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

    public MVec2(Point begin, Point end) {
        this(end.x - begin.x, end.y - begin.y);
    }

    public MVec2(MVec2 begin, MVec2 end) {
        this(end.x - begin.x, end.y - begin.y);
    }

    public MVec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getLengthSquare() {
        return x * x + y * y;
    }

    public float getLength() {
        return (float) Math.sqrt(getLengthSquare());
    }

    public MVec2 getNormal() {
        return new MVec2(-this.y, this.x);
    }

    public MVec2 getNormalized() {
        float length = getLength();

        return new MVec2(
                x / length,
                y / length
        );
    }

    public MVec2 normalize() {
        float length = getLength();

        x /= length;
        y /= length;

        return this;
    }

    public float getDotProduct(MVec2 right) {
        return this.x * right.x + this.y * right.y;
    }

    public boolean compare(MVec2 right) {
        return
                this.x == right.x &&
                this.y == right.y;
    }
}