package org.mlib.Graphics.Shape.Tod;

import org.mlib.Graphics.Base.Vertex.MVertex2;
import org.mlib.Graphics.Scene.MScene;
import org.mlib.Graphics.Tools.MColor;
import org.mlib.Graphics.Tools.MVec2;
import org.mlib.System.Exception.MException;
import static android.opengl.GLES20.GL_TRIANGLES;

public class MRectangle extends MShape {
    public MRectangle(MVec2 size) {
        MVertex2[] vertices = new MVertex2[6];

        vertices[0] = new MVertex2(new MVec2(), new MVec2(), new MColor());
        vertices[1] = new MVertex2(new MVec2(0, size.y), new MVec2(), new MColor());
        vertices[2] = new MVertex2(new MVec2(size.x, 0), new MVec2(), new MColor());
        vertices[3] = new MVertex2(new MVec2(0, size.y), new MVec2(), new MColor());
        vertices[4] = new MVertex2(new MVec2(size.x, 0), new MVec2(), new MColor());
        vertices[5] = new MVertex2(new MVec2(size.x, size.y), new MVec2(), new MColor());

        for (MVertex2 vertex : vertices)
            super.setVertex(vertex);

        super.setPrimitive(GL_TRIANGLES);
    }

    public MRectangle(MVec2 size, MScene scene) {
        this(size);
        scene.setDrawable(this);
    }

    public void setSize(MVec2 size) {
        scale(
                new MVec2(
                        size.x / this.getSize().x,
                        size.y / this.getSize().y
                )
        );

        updateRectSize(size);
    }

    @Override
    public void setPrimitive(int primitive) {
        new MException("В этом классе нельзя изменять примитивный тип").printStackTrace();
    }

    @Override
    protected void calculateRect() {
        getRect().left = getVertices().get(0).position.x;
        getRect().top = getVertices().get(0).position.y;
        getRect().right = getVertices().get(5).position.x;
        getRect().bottom = getVertices().get(5).position.y;
    }
}