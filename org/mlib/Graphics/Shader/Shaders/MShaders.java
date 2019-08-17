package org.mlib.Graphics.Shader.Shaders;

import android.util.Pair;

public class MShaders {
    public static Pair<String, String> main =
            new Pair<>(
                            "attribute vec4 iVertex;" +
                            "attribute vec4 iColor;" +
                            "attribute vec4 iTexCoord;" +
                            "varying vec4 oColor;" +
                            "varying vec4 oTexCoord;" +
                            "uniform mat4 iMatrix;" +
                            "void main()" +
                            "{" +
                            "gl_Position = iMatrix * iVertex;" +
                            "oColor = iColor;" +
                            "oTexCoord = iTexCoord;" +
                            "}",

                            "precision mediump float;" +
                            "varying vec4 oColor;" +
                            "varying vec4 oTexCoord;" +
                            "uniform sampler2D iTexture;" +
                            "void main()" +
                            "{" +
                            "gl_FragColor = texture2D(iTexture, vec2(oTexCoord.x, oTexCoord.y)) + oColor;" +
                            "}"
            );
}