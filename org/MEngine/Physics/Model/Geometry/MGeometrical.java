package org.MEngine.Physics.Model.Geometry;

abstract public class MGeometrical {
    public enum Type {
        Circle,
        Polygone
    }
    private final Type type;
    protected MAABB aabb;

    protected MGeometrical(Type type) {
        this.type = type;
        this.aabb = new MAABB();
    }

    public Type getType() {
        return this.type;
    }

    public MAABB getAABB() {
        return this.aabb;
    }

    abstract public boolean isCollision(MGeometrical right);

    abstract protected void buildAABB();
}