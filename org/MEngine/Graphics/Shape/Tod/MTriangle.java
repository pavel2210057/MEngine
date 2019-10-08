package org.MEngine.Graphics.Shape.Tod;

import org.MEngine.Graphics.Base.Vertex.MVertex2;
import org.MEngine.Graphics.Scene.MScene;
import org.MEngine.Graphics.Tools.MVec2;
import org.MEngine.System.Exception.MException;

import static android.opengl.GLES20.GL_TRIANGLES;

public class MTriangle extends MShape {
    public MTriangle() {}

    public MTriangle(MScene scene) {
        super.update(scene.getView());
    }

    public MTriangle(MVec2 first, MVec2 second, MVec2 third) {
        MVertex2[] vertices = new MVertex2[] {
            new MVertex2(),
            new MVertex2(),
            new MVertex2()
        };

        vertices[0].position = first;
        vertices[1].position = second;
        vertices[2].position = third;

        super.setVertex(vertices[0]);
        super.setVertex(vertices[1]);
        super.setVertex(vertices[2]);

        super.setPrimitive(GL_TRIANGLES);
    }

    public MTriangle(MVec2 first, MVec2 second, MVec2 third, MScene scene) {
        this(first, second, third);
        super.update(scene.getView());
    }

    public void setVertex(MVertex2 vertex, int index) {
        removeVertex(index);
        super.setVertex(vertex, index);
    }

    @Override
    public void setPrimitive(int primitive) {
        new MException("В этом классе нельзя изменять примитивный тип").printStackTrace();
    }
}