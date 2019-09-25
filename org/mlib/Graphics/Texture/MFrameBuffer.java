package org.mlib.Graphics.Texture;

import org.mlib.Graphics.Shape.MDrawable;
import org.mlib.Graphics.Shape.Tod.MSprite;
import org.mlib.Graphics.Tools.MVec2;
import org.mlib.System.DeviceService.MDevice;
import org.mlib.System.Exception.MException;
import static android.opengl.GLES20.GL_COLOR_ATTACHMENT0;
import static android.opengl.GLES20.GL_DEPTH_ATTACHMENT;
import static android.opengl.GLES20.GL_FRAMEBUFFER;
import static android.opengl.GLES20.GL_FRAMEBUFFER_COMPLETE;
import static android.opengl.GLES20.GL_TEXTURE2;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glBindFramebuffer;
import static android.opengl.GLES20.glCheckFramebufferStatus;
import static android.opengl.GLES20.glFramebufferTexture2D;
import static android.opengl.GLES20.glGenFramebuffers;

public class MFrameBuffer {
    MTexture texture;
    private int framebuffer;
    private MVec2 size;

    public MFrameBuffer(MDevice device) {
        this(device.getResolution().x, device.getResolution().y);
    }

    public MFrameBuffer(int width, int height) {
        this.size = new MVec2(width, height);

        this.texture = new MTexture(MTextureReader.load(width, height));

        setFramebuffer(this.texture.getTexture());
    }

    public void setFramebuffer(int texture) {
        this.framebuffer = load(texture);
    }

    public MDrawable getDrawable() {
        MSprite sprite = new MSprite(this.size);

        sprite.setTexture(this.texture);

        return sprite;
    }

    public int getFramebuffer() {
        return this.framebuffer;
    }

    public MTexture getTexture() {
        return this.texture;
    }

    public MVec2 getSize() {
        return this.size;
    }

    public static int[] createFramebuffers(int size) {
        final int[] buffers = new int[size];

        glGenFramebuffers(size, buffers, 0);

        return buffers;
    }

    public static void bind(int framebuffer) {
        glBindFramebuffer(GL_FRAMEBUFFER, framebuffer);
    }

    public static int load(int texture) {
        int framebuffer = createFramebuffers(1)[0];
        bind(framebuffer);

        MTexture.bind(texture);

        glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_COLOR_ATTACHMENT0,
                GL_TEXTURE_2D,
                texture,
                0
        );

        int status = glCheckFramebufferStatus(GL_FRAMEBUFFER);
        if (status != GL_FRAMEBUFFER_COMPLETE)
            new MException("Framebuffer error. Status: " + status).printStackTrace();

        bind(0);

        return framebuffer;
    }
}