package org.MEngine.Graphics.Base.Context;

import android.opengl.EGLContext;
import android.opengl.GLSurfaceView;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import org.MEngine.Graphics.Base.Renderer.MRenderer;
import org.MEngine.System.DeviceService.MDevice;
import org.MEngine.System.Exception.MException;

public class MContextManager {
    private GLSurfaceView glView;
    private MRenderer renderer;

    public MContextManager(MDevice device, MRenderer renderer) {
        if (!device.getEs2Supported())
            new MException("Устройство не поддерживает GLES2.0!").printStackTrace();

        update(device, renderer);
    }

    public MContextManager(MDevice device, MRenderer renderer, ViewGroup group) {
        if (!device.getEs2Supported())
            new MException("Устройство не поддерживает GLES2.0!").printStackTrace();

        update(device, renderer, group);
    }

    public MContextManager(MDevice device, MRenderer renderer, @IdRes int id) {
        if (!device.getEs2Supported())
            new MException("Устройство не поддерживает GLES2.0!").printStackTrace();

        update(device, renderer, id);
    }

    public void update(MDevice device, MRenderer renderer) {
        GLSurfaceView view = new GLSurfaceView(device.getActivity());
        view.setEGLContextClientVersion(2);
        view.setRenderer(this.renderer = renderer);
        device.getActivity().setContentView(view);

        this.glView = view;
    }

    public void update(MDevice device, MRenderer renderer, ViewGroup group) {
        GLSurfaceView view = new GLSurfaceView(device.getActivity());
        view.setEGLContextClientVersion(2);
        view.setRenderer(this.renderer = renderer);
        group.addView(view);

        this.glView = view;
    }

    public void update(MDevice device, MRenderer renderer, @IdRes int id) {
        GLSurfaceView view = device.getActivity().findViewById(id);
        view.setEGLContextClientVersion(2);
        view.setRenderer(this.renderer = renderer);

        this.glView = view;
    }

    public GLSurfaceView getView() {
        return this.glView;
    }

    public MRenderer getRenderer() {
        return this.renderer;
    }
}