package org.mlib.Graphics.Scene;

import android.opengl.GLSurfaceView.Renderer;
import org.mlib.Graphics.Base.Context.MContextListener;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MRenderer implements Renderer {
    private MContextListener listener;

    public MRenderer() {
        this.listener = new MContextListener();
    }

    public MRenderer(MContextListener listener) {
        setListener(listener);
    }

    public void setListener(MContextListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        listener.onCreate();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        listener.onUpdate();
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        listener.onDraw();
    }
}