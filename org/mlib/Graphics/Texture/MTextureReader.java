package org.mlib.Graphics.Texture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import androidx.annotation.DrawableRes;
import org.mlib.System.DeviceService.MDevice;
import static android.opengl.GLES20.GL_LINEAR;
import static android.opengl.GLES20.GL_RGB;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.GL_UNSIGNED_BYTE;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glTexImage2D;
import static android.opengl.GLES20.glTexParameteri;

public class MTextureReader {
    public static int load(MDevice device, @DrawableRes int resource) {
        int texture = MTexture.createTextures(1)[0];

        final Bitmap bitmap = BitmapFactory.decodeResource(
                device.getActivity().getResources(),
                resource
        );

        glActiveTexture(GL_TEXTURE0);
        MTexture.bind(texture);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);

        bitmap.recycle();
        MTexture.bind(0);

        return texture;
    }

    public static int load(int width, int height) {
        int texture = MTexture.createTextures(1)[0];
        MTexture.bind(texture);

        glActiveTexture(GL_TEXTURE0);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height,
                0, GL_RGB, GL_UNSIGNED_BYTE, null);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

        MTexture.bind(0);

        return texture;
    }
}