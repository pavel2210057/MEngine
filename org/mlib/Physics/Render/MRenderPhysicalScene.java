package org.mlib.Physics.Render;

import org.mlib.Graphics.Scene.MScene;
import org.mlib.Graphics.Tools.MMat4;
import org.mlib.Physics.Scene.MPhysicalScene;

import java.util.Vector;

public class MRenderPhysicalScene extends MPhysicalScene implements MRenderable {
    private Vector<MRenderable> renderables;

    public MRenderPhysicalScene() {
        this(new Vector<>());
    }

    public MRenderPhysicalScene(Vector<MRenderable> renderables) {
        this.renderables = renderables;

        getListener().getEvents().add(
                () -> {
                    syncGeometry();
                    return true;
                }
        );
    }

    public void setRenderable(MRenderable renderable) {
        this.renderables.add(renderable);
    }

    public void setRenderable(MRenderable renderable, MScene scene) {
        setRenderable(renderable);
        scene.setDrawable(renderable);
    }

    public Vector<MRenderable> getRenderables() {
        return this.renderables;
    }

    @Override
    public void syncGeometry() {
        for (MRenderable renderable : this.renderables)
            renderable.syncGeometry();
    }

    @Override
    public void update(MMat4 view) {
        for (MRenderable renderable : this.renderables)
            renderable.update(view);
    }

    @Override
    public void draw() {
        for (MRenderable renderable : this.renderables)
            renderable.draw();
    }
}