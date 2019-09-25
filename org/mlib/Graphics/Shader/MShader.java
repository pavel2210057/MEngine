package org.mlib.Graphics.Shader;

import android.util.Pair;
import android.util.SparseArray;

import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import org.mlib.System.DeviceService.MDevice;
import org.mlib.System.Exception.MException;
import org.mlib.System.File.MFile;
import org.mlib.System.File.MRawReader;
import java.nio.FloatBuffer;
import java.util.HashMap;

import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUseProgram;

public class MShader {
    private String vertexSource, fragmentSource;
    private int vertex, fragment, program;
    private MUniformArray uniformArray;
    private MBufferArray bufferArray;
    private HashMap<String, Integer> cachedVars;
    private boolean isCompiled;

    public MShader() {
        this.isCompiled = false;
        this.uniformArray = new MUniformArray();
        this.bufferArray = new MBufferArray();
        this.cachedVars = new HashMap<>();
    }

    public MShader(String source, int type) {
        this();
        setSource(source, type);
    }

    public MShader(String vertexSource, String fragmentSource) {
        this();
        setVertexSource(vertexSource);
        setFragmentSource(fragmentSource);
    }

    public MShader(MFile file, int type) {
        this();
        loadFromFile(file, type);
    }

    public MShader(MFile vertexFile, MFile fragmentFile) {
        this();
        loadFromFile(vertexFile, fragmentFile);
    }

    public MShader(MDevice device, @RawRes int resource, int type) {
        this();
        loadFromRaw(device, resource, type);
    }

    public void loadFromFile(MFile file, int type) {
        setSource(file.read(), type);
    }

    public void loadFromFile(MFile vertexFile, MFile fragmentFile) {
        setVertexSource(vertexFile.read());
        setFragmentSource(fragmentFile.read());
    }

    public void loadFromRaw(MDevice device, @RawRes int resource, int type) {
        setSource(MRawReader.read(device, resource), type);
    }

    public void setSource(String source, int type) {
        if (type == GL_VERTEX_SHADER)
            setVertexSource(source);
        else if (type == GL_FRAGMENT_SHADER)
            setFragmentSource(source);
    }

    public void setVertexSource(String vertexSource) {
        this.vertexSource = vertexSource;
        this.isCompiled = false;
        delete();
    }

    public void setFragmentSource(String fragmentSource) {
        this.fragmentSource = fragmentSource;
        this.isCompiled = false;
        delete();
    }

    public void setUniform(String name, float[] values) {
        compile();

        Integer uniform;
        if ((uniform = getVarFromCache(name)) == null)
            this.cachedVars.put(
                    name,
                    uniform = glGetUniformLocation(this.program, name)
            );

        this.uniformArray.setUniform(uniform, values);
    }

    public void setAttribute(String name, FloatBuffer values, int size) {
        compile();

        Integer attribute;
        if ((attribute = getVarFromCache(name)) == null)
            this.cachedVars.put(
                    name,
                    attribute = glGetAttribLocation(this.program, name)
            );

        this.bufferArray.addAttribute(attribute, values, size, 0);
    }

    public String getVertexSource() {
        return this.vertexSource;
    }

    public String getFragmentSource() {
        return this.fragmentSource;
    }

    public int getVertex() {
        return this.vertex;
    }

    public int getFragment() {
        return this.fragment;
    }

    public int getProgram() {
        return this.program;
    }

    public MBufferArray getBufferArray() {
        return this.bufferArray;
    }

    public MUniformArray getUniformArray() {
        return this.uniformArray;
    }

    public HashMap<String, Integer> getCachedVars() {
        return this.cachedVars;
    }

    public Integer getVarFromCache(String name) {
        return this.cachedVars.get(name);
    }

    public void apply() {
        glUseProgram(this.program);

        for (int i = 0; i < this.bufferArray.getBuffers().size(); ++i) {
            int attribute = this.bufferArray.getBuffers().keyAt(i);
            Pair<Integer, Integer> value = this.bufferArray.getBuffers().valueAt(i);

            MBufferArray.useBuffer(attribute, value.first, value.second);
        }

        for (int i = 0; i < this.uniformArray.getUniforms().size(); ++i) {
            int uniform = this.uniformArray.getUniforms().keyAt(i);
            float[] values = this.uniformArray.getUniforms().valueAt(i);

            MUniformArray.useUniform(uniform, values);
        }
    }

    public void delete() {
        glDeleteShader(this.vertex);
        glDeleteShader(this.fragment);
        glDeleteProgram(this.program);
    }

    private void compile() {
        if (!this.isCompiled) {
            if (this.vertexSource.length() == 0 || this.fragmentSource.length() == 0)
                new MException("Недостаточно данных для компиляции шейдера").printStackTrace();
            else {
                int[] result = MShaderCompiler.compile(this.vertexSource, this.fragmentSource);

                this.vertex = result[0];
                this.fragment = result[1];
                this.program = result[2];

                this.isCompiled = true;
            }
        }
    }
}