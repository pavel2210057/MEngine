package org.mlib.Graphics.Shape.Tod;

import org.mlib.Graphics.Base.Vertex.MVertex2;
import org.mlib.Graphics.Shader.MShader;
import org.mlib.Graphics.Shader.Shaders.MShaders;
import org.mlib.Graphics.Shape.MDrawable;
import org.mlib.Graphics.Shape.MTransformable;
import org.mlib.Graphics.Tools.MColor;
import org.mlib.Graphics.Tools.MMat4;
import org.mlib.Graphics.Tools.MRect;
import org.mlib.Graphics.Tools.MVec2;
import org.mlib.System.Buffer.MFloatBuffer;
import java.nio.FloatBuffer;
import java.util.Vector;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.glDrawArrays;

public class MShape implements MDrawable, MTransformable {
    private int primitive;
    private Vector<MVertex2> vertices;
    private MMat4 model;
    private MShader shader;
    private MVec2 position;
    private MRect rect;

    public MShape() {
        this.primitive = GL_POINTS;
        this.vertices = new Vector<>();
        this.model = new MMat4();
        this.shader = new MShader (
                MShaders.main.first,
                MShaders.main.second
        );
        this.position = new MVec2();
    }

    public void setPrimitive(int primitive) {
        this.primitive = primitive;
    }

    public void setVertex(MVertex2 vertex) {
        this.vertices.add(vertex);
        calculateRect();
    }

    public void setVertex(MVertex2 vertex, int index) {
        this.vertices.add(index, vertex);
        calculateRect();
    }

    public void setVertices(Vector<MVertex2> vertices) {
        this.vertices.addAll(vertices);
    }

    public void setColor(MColor color) {
        for (MVertex2 vertex : this.vertices)
            vertex.color = color;
        calculateRect();
    }

    public void removeVertex(int index) {
        this.vertices.removeElementAt(index);
        calculateRect();
    }

    public void setShader(MShader shader) {
        this.shader = shader;
    }

    public int getPrimitive() {
        return this.primitive;
    }

    public Vector<MVertex2> getVertices() {
        return this.vertices;
    }

    public MShader getShader() {
        return this.shader;
    }

    public MRect getRect() {
        return this.rect;
    }

    @Override
    public void update(MMat4 view) {
        this.model.identity(0);
        this.model.mul(view);

        FloatBuffer
                position = MFloatBuffer.create(this.vertices.size() * 8),
                color = MFloatBuffer.create(this.vertices.size() * 16),
                texCoord = MFloatBuffer.create(this.vertices.size() * 8);

        for (MVertex2 vertex : this.vertices) {
            position.put(new float[] { vertex.position.x, vertex.position.y });
            color.put(new float[] { vertex.color.r, vertex.color.g, vertex.color.b, vertex.color.a });
            texCoord.put(new float[] { vertex.texCoord.x, vertex.texCoord.y });
        }

        position.position(0);
        color.position(0);
        texCoord.position(0);

        this.shader.setUniform("iMatrix", this.model.getComponents());

        this.shader.setAttribute("iVertex", position, 2);
        this.shader.setAttribute("iColor", color, 4);
        this.shader.setAttribute("iTexCoord", texCoord, 2);
    }

    @Override
    public void draw() {
        this.shader.apply();
        glDrawArrays(this.primitive, 0, this.vertices.size());
    }

    @Override
    public void setPosition(MVec2 position) {
        move(new MVec2(position.x - this.position.x, position.y - this.position.y));
    }

    @Override
    public void move(MVec2 offset) {
        this.model.translate(offset);

        this.position.x += offset.x;
        this.position.y += offset.y;

        this.shader.setUniform("matrix", this.model.getComponents());
    }

    @Override
    public void scale(MVec2 factor) {
        this.model.scale(factor);
        this.shader.setUniform("matrix", this.model.getComponents());
    }

    @Override
    public MVec2 getPosition() {
        return this.position;
    }

    @Override
    public MVec2 getSize() {
        return new MVec2(this.rect.right - this.rect.left,this.rect.bottom - this.rect.top);
    }

    public boolean compare(MShape right) {
        return
                this.primitive == right.getPrimitive() &&
                this.shader.getProgram() == right.getShader().getProgram();
    }

    protected void calculateRect() {
        float left = 0, top = 0, right = 0, bottom = 0;

        for (MVertex2 vertex : this.vertices) {
            MVec2 pos = vertex.position;

            if (pos.x < left)
                left = pos.x;
            else if (pos.x > right)
                right = pos.x;

            if (pos.y < top)
                top = pos.y;
            else if (pos.x > bottom)
                bottom = pos.y;
        }

        this.rect = new MRect(left, top, right, bottom);
    }
}