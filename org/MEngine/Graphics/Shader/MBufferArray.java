package org.MEngine.Graphics.Shader;

import android.util.Pair;
import android.util.SparseArray;
import java.nio.FloatBuffer;
import static android.opengl.GLES20.GL_ARRAY_BUFFER;
import static android.opengl.GLES20.GL_DYNAMIC_DRAW;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glBindBuffer;
import static android.opengl.GLES20.glBufferData;
import static android.opengl.GLES20.glBufferSubData;
import static android.opengl.GLES20.glDeleteBuffers;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGenBuffers;
import static android.opengl.GLES20.glVertexAttribPointer;

public class MBufferArray {
    private SparseArray<Pair<Integer, Integer>> buffers; //attr id -> (buffer, size)

    public MBufferArray() {
        this.buffers = new SparseArray<>();
    }

    public void addAttribute(int attribute, FloatBuffer values, int size, int offset) {
        int index = this.buffers.indexOfKey(attribute);
        int buffer;

        if (index > -1) {
            buffer = this.buffers.get(attribute).first;
            subDataBuffer(buffer, values, offset);
        } else {
            buffer = createBuffers(1)[0];
            dataBuffer(buffer, values);
            addBuffer(attribute, buffer, size);
        }
    }

    public void addBuffer(int attribute, int buffer, int size) {
        this.buffers.put(attribute, new Pair<>(buffer, size));
    }

    public void removeBuffer(int index) {
        this.buffers.removeAt(index);
    }

    public int getBuffer(int attribute) {
        return this.buffers.get(attribute).first;
    }

    public int getSize(int attribute) {
        return this.buffers.get(attribute).second;
    }

    public SparseArray<Pair<Integer, Integer>> getBuffers() {
        return this.buffers;
    }

    public static int[] createBuffers(int size) {
        final int[] buffers = new int[size];
        glGenBuffers(size, buffers, 0);
        return buffers;
    }

    public static void bind(int buffer) {
        glBindBuffer(GL_ARRAY_BUFFER, buffer);
    }

    public static void useBuffer(int attribute, int buffer, int size) {
        bind(buffer);
        glVertexAttribPointer(attribute, size, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(attribute);
        bind(0);
    }

    public static void dataBuffer(int buffer, FloatBuffer values) {
        values.position(0);

        bind(buffer);
        glBufferData(GL_ARRAY_BUFFER, values.capacity() * 4, values, GL_DYNAMIC_DRAW);
        bind(0);
    }

    public static void subDataBuffer(int buffer, FloatBuffer values, int offset) {
        bind(buffer);
        glBufferSubData(GL_ARRAY_BUFFER, offset, values.capacity() * 4, values);
        bind(0);
    }

    public static void deleteBuffer(int[] buffers) {
        glDeleteBuffers(buffers.length, buffers, 0);
    }
}