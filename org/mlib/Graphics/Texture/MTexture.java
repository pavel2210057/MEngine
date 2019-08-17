package org.mlib.Graphics.Texture;

import androidx.annotation.DrawableRes;
import org.mlib.System.DeviceService.MDevice;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDeleteTextures;
import static android.opengl.GLES20.glGenTextures;

public class MTexture {
    private int texture;

    public MTexture() {}

    public MTexture(int texture) {
        setTexture(texture);
    }

    public MTexture(MDevice device, @DrawableRes int resource) {
        loadFromRaw(device, resource);
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public void loadFromRaw(MDevice device, @DrawableRes int resource) {
        this.texture = MTextureReader.load(device, resource);
    }

    public int getTexture() {
        return this.texture;
    }

    public static int[] createTextures(int size) {
        final int[] textures = new int[size];

        glGenTextures(size, textures, 0);

        return textures;
    }

    public static void bind(int texture) {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public static void deleteTextures(int[] textures) {
        glDeleteTextures(textures.length, textures, 0);
    }
}