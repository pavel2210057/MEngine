package org.mlib.Graphics.Shape.Tod;

import org.mlib.Graphics.Base.Vertex.MVertex2;
import org.mlib.Graphics.Tools.MVec2;
import org.mlib.System.Exception.MException;

import static android.opengl.GLES20.GL_TRIANGLES;

public class MRectangle extends MShape {
    public MRectangle() {
        MVertex2[] vertices = new MVertex2[] {
                new MVertex2(),
                new MVertex2(),
                new MVertex2(),
                new MVertex2(),
                new MVertex2(),
                new MVertex2(),
        };

        vertices[0].position = new MVec2(0);
        vertices[1].position = new MVec2(0, 1000);
        vertices[2].position = new MVec2(1000, 0);
        vertices[3].position = new MVec2(0, 1000);
        vertices[4].position = new MVec2(1000, 0);
        vertices[5].position = new MVec2(1000, 1000);

        super.setVertex(vertices[0]);
        super.setVertex(vertices[1]);
        super.setVertex(vertices[2]);
        super.setVertex(vertices[3]);
        super.setVertex(vertices[4]);
        super.setVertex(vertices[5]);

        super.setPrimitive(GL_TRIANGLES);
    }

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

    public void setSize(MVec2 size) {
       // this.meshes[0].scale(size);
       // this.meshes[1].scale(size);
    }

    @Override
    public void setPrimitive(int primitive) {
        new MException("В этом классе нельзя изменять примитивный тип").printStackTrace();
    }
}