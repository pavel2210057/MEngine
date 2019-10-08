package org.mlib.Graphics.Scene;

import android.view.ViewGroup;
import androidx.annotation.IdRes;
import org.mlib.Graphics.Base.Context.MContextListener;
import org.mlib.Graphics.Base.Context.MContextManager;
import org.mlib.Graphics.Shape.MDrawable;
import org.mlib.Graphics.Texture.MFrameBuffer;
import org.mlib.Graphics.Tools.MMat4;
import org.mlib.System.DeviceService.MDevice;
import org.mlib.Graphics.Base.Renderer.MRenderer;
import java.util.Vector;
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.glClear;

public class MScene {
    private MMat4 view;
    private Vector<MDrawable> drawables;
    private MContextListener listener;
    private MContextManager manager;

    public MScene(MDevice device) {
        this.view = new MMat4();
        this.view.setOrtho(device.getResolution().x, device.getResolution().y);
        this.drawables = new Vector<>();

        this.listener = new MContextListener();
        this.manager = new MContextManager(device, new MRenderer(this.listener));
    }

    public MScene(MDevice device, ViewGroup group) {
        this.view = new MMat4();
        this.view.setOrtho(device.getResolution().x, device.getResolution().y);
        this.drawables = new Vector<>();

        this.listener = new MContextListener();
        this.manager = new MContextManager(device, new MRenderer(this.listener), group);
    }

    public MScene(MDevice device, @IdRes int id) {
        this.view = new MMat4();
        this.view.setOrtho(device.getResolution().x, device.getResolution().y);
        this.drawables = new Vector<>();

        this.listener = new MContextListener();
        this.manager = new MContextManager(device, new MRenderer(this.listener), id);
    }

    public void setView(MMat4 view) {
        this.view = view;
    }

    public void setDrawable(MDrawable drawable) {
        drawable.update(view);
        this.drawables.add(drawable);
    }

    public void setDrawables(MDrawable[] drawables) {
        for (MDrawable drawable : drawables)
            setDrawable(drawable);
    }

    public void resetFramebuffer() {
        MFrameBuffer.bind(0);
    }

    public void setFramebuffer(MFrameBuffer framebuffer) {
        MFrameBuffer.bind(framebuffer.getFramebuffer());
    }

    public MMat4 getView() {
        return this.view;
    }

    public Vector<MDrawable> getDrawables() {
        return this.drawables;
    }

    public MContextListener getListener() {
        return this.listener;
    }

    public MContextManager getManager() {
        return this.manager;
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for (MDrawable drawable : this.drawables)
            drawable.draw();
    }
}