package org.MEngine.Graphics.Shape.Tod;

import org.MEngine.Graphics.Scene.MScene;
import org.MEngine.Graphics.Tools.MVec2;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;

public class MRegularPolygon extends MShape {
    private MVec2 center;
    private float radius;

    public MRegularPolygon(float radius) {
        super();

        setRadius(radius);
        setCenter(new MVec2());
        setVertexCount(16);
        setPrimitive(GL_TRIANGLE_FAN);
    }

    public MRegularPolygon(float radius, MScene scene) {
        this(radius);
        scene.setDrawable(this);
    }

    public void setCenter(MVec2 center) {
        super.setPosition(center);
        this.center = center;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void updateVertices() {
        MVec2 ref = new MVec2(
                this.center.x + this.radius,
                this.center.y + this.radius
        );
        float
                step = 2 * (float)Math.PI / getVertices().size(),
                currentAngle;

        for (int i = 0; i < getVertices().size(); ++i) {
            currentAngle = step * i;

            getVertices().get(i).position.x =
                    this.center.x +
                    (ref.x - this.center.x) * (float) Math.cos(currentAngle) -
                    (ref.y - this.center.y) * (float) Math.sin(currentAngle);
            getVertices().get(i).position.y =
                    this.center.y +
                    (ref.y - this.center.y) * (float)Math.cos(currentAngle) +
                    (ref.x - this.center.x) * (float)Math.sin(currentAngle);
        }

        calculateRect();
    }

    public MVec2 getCenter() {
        return this.center;
    }

    public float getRadius() {
        return this.radius;
    }

    @Override
    public void setVertexCount(int count) {
        super.setVertexCount(count);

        updateVertices();
        calculateRect();
    }

    @Override
    protected void calculateRect() {
        getRect().left = this.center.x - this.radius;
        getRect().top = this.center.y - this.radius;
        getRect().right = this.center.x + this.radius;
        getRect().bottom = this.center.y + this.radius;
    }
}