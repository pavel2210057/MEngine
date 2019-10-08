package org.MEngine.Graphics.Shader;

import org.MEngine.System.Exception.MException;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glGetProgramInfoLog;
import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;

public class MShaderCompiler {
    public static int[] compile(String vertexSource, String fragmentSource) {
        int
                vertex = glCreateShader(GL_VERTEX_SHADER),
                fragment = glCreateShader(GL_FRAGMENT_SHADER),
                program = glCreateProgram();
        String info;

        glShaderSource(vertex, vertexSource);
        glCompileShader(vertex);

        info = glGetShaderInfoLog(vertex);
        if (info.length() > 0)
            new MException(info).printStackTrace();

        glShaderSource(fragment, fragmentSource);
        glCompileShader(fragment);

        info = glGetShaderInfoLog(fragment);
        if (info.length() > 0)
            new MException(info).printStackTrace();

        glAttachShader(program, vertex);
        glAttachShader(program, fragment);

        glLinkProgram(program);

        info = glGetProgramInfoLog(program);
        if (info.length() > 0)
            new MException(info).printStackTrace();

        return new int[] { vertex, fragment, program };
    }
}