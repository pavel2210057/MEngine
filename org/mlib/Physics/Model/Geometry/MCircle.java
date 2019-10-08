package org.mlib.Physics.Model.Geometry;

import org.mlib.Graphics.Tools.MVec2;
import org.mlib.Physics.Dynamic.Collision.MCircleNCircle;

public class MCircle extends MGeometrical {
    private MVec2 center;
    private float radius;

    public MCircle() {
        this(new MVec2(), 0);
    }

    public MCircle(MVec2 center, float radius) {
        super(Type.Circle);

        this.center = center;
        this.radius = radius;
        buildAABB();
    }

    public void setCenter(MVec2 center) {
        this.center = center;
        buildAABB();
    }

    public void setRadius(float radius) {
        this.radius = radius;
        buildAABB();
    }

    public MVec2 getCenter() {
        return this.center;
    }

    public float getRadius() {
        return this.radius;
    }

    @Override
    public boolean isCollision(MGeometrical right) {
        if (!this.aabb.isCollision(right.getAABB()))
            return false;

        if (right instanceof MCircle)
                return MCircleNCircle.isCollision(this, (MCircle) right);

        return false;
    }

    @Override
    protected void buildAABB() {
        this.aabb.left = this.center.x - this.radius;
        this.aabb.top = this.center.y - this.radius;
        this.aabb.right = this.center.x + this.radius;
        this.aabb.bottom = this.center.y + this.radius;
    }
}