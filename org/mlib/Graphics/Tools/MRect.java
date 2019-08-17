package org.mlib.Graphics.Tools;

public class MRect {
    public float left, top, right, bottom;
    public MVec2 center;

    public MRect() {
        this(0, 0, 0, 0);
    }

    public MRect(MVec2 begin, MVec2 end) {
        this(begin.x, begin.y, end.x, end.y);
    }

    public MRect(float left, float top, float right, float bottom) {
        setRect(left, top, right, bottom);
    }

    public void setRect(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;

        this.center = new MVec2((right - left) / 2, (bottom - top) / 2);
    }
}