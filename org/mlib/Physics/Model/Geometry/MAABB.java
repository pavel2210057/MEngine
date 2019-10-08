package org.mlib.Physics.Model.Geometry;

import org.mlib.Graphics.Tools.MVec2;

public class MAABB {
    public float left, top, right, bottom;

    public MAABB() {
        this(0, 0, 0, 0);
    }

    public MAABB(MVec2 begin, MVec2 end) {
        this(begin.x, begin.y, end.x, end.y);
    }

    public MAABB(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public boolean isCollision(MAABB right) {
        return
                this.right >= right.left && this.left <= right.right &&
                (this.bottom >= right.top || this.top <= right.bottom);
    }

    public void move(MVec2 offset) {
        this.left += offset.x;
        this.top += offset.y;
        this.right += offset.x;
        this.bottom += offset.y;
    }

    public void setPosition(MVec2 offset) {
        move(new MVec2(offset.x - this.left, offset.y - this.top));
    }

    public MVec2 getPosition() {
        return new MVec2(this.left, this.top);
    }

    public MVec2[] getPoints() {
        return new MVec2[] {
                new MVec2(this.left, this.top),
                new MVec2(this.right, this.top),
                new MVec2(this.left, this.bottom),
                new MVec2(this.right, this.bottom)
        };
    }
}