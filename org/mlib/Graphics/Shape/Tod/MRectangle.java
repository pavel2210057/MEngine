package org.mlib.Graphics.Shape.Tod;

import org.mlib.Graphics.Base.Vertex.MVertex2;
import org.mlib.Graphics.Scene.MScene;
import org.mlib.Graphics.Tools.MVec2;
import org.mlib.System.Exception.MException;
import static android.opengl.GLES20.GL_TRIANGLES;

public class MRectangle extends MShape {
    public MRectangle(MVec2 size) {
        MVertex2[] vertices = new MVertex2[] {
                new MVertex2(),
                new MVertex2(),
                new MVertex2(),
                new MVertex2(),
                new MVertex2(),
                new MVertex2(),
        };

        vertices[0].position = new MVec2(0);
        vertices[1].position = new MVec2(0, size.y);
        vertices[2].position = new MVec2(size.x, 0);
        vertices[3].position = new MVec2(0, size.y);
        vertices[4].position = new MVec2(size.x, 0);
        vertices[5].position = new MVec2(size.x, size.y);

        super.setVertex(vertices[0]);
        super.setVertex(vertices[1]);
        super.setVertex(vertices[2]);
        super.setVertex(vertices[3]);
        super.setVertex(vertices[4]);
        super.setVertex(vertices[5]);

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
}