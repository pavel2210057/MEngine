package org.mlib.Graphics.Base.Renderer;

import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLSurfaceView.Renderer;
import org.mlib.Graphics.Base.Context.MContextListener;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.EGL14.EGL_DRAW;
import static android.opengl.EGL14.eglGetCurrentContext;
import static android.opengl.EGL14.eglGetCurrentDisplay;
import static android.opengl.EGL14.eglGetCurrentSurface;

public class MRenderer implements Renderer {
    private EGLContext context;
    private EGLDisplay display;
    private EGLSurface surface;
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

    public EGLContext getContext() {
        return this.context;
    }

    public EGLDisplay getDisplay() {
        return this.display;
    }

    public EGLSurface getSurface() {
        return this.surface;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        this.context = eglGetCurrentContext();
        this.display = eglGetCurrentDisplay();
        this.surface = eglGetCurrentSurface(EGL_DRAW);

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