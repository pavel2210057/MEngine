package org.mlib.Graphics.Shader;

import android.util.SparseArray;

import org.mlib.System.Buffer.MFloatBuffer;

import java.nio.FloatBuffer;
import java.util.Vector;

import static android.opengl.GLES20.glUniform1f;
import static android.opengl.GLES20.glUniform2f;
import static android.opengl.GLES20.glUniform3f;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;

public class MUniformArray {
    private SparseArray<float[]> uniforms; //uniform id -> values

    public MUniformArray() {
        this.uniforms = new SparseArray<>();
    }

    public void setUniform(int uniform, float[] values) {
        this.uniforms.put(uniform, values);
    }

    public void removeUnifrorm(int index) {
        this.uniforms.remove(index);
    }

    public float[] getUniform(int uniform) {
        return this.uniforms.get(uniform);
    }

    public SparseArray<float[]> getUniforms() {
        return this.uniforms;
    }

    public static void useUniform(int uniform, float[] values) {
        switch (values.length) {
            case 1:
                glUniform1f(uniform, values[0]);
                break;
            case 2:
                glUniform2f(uniform, values[0], values[1]);
                break;
            case 3:
                glUniform3f(uniform, values[0], values[1], values[2]);
                break;
            case 4:
                glUniform4f(uniform, values[0], values[1], values[2], values[3]);
                break;

            case 16:
                FloatBuffer buffer = MFloatBuffer.create(values);
                buffer.position(0);

                glUniformMatrix4fv(uniform, 1, false, buffer);
                break;
        }
    }
}