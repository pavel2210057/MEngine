package org.MEngine.Physics.Render;

import org.MEngine.Graphics.Shape.MDrawable;

public interface MRenderable extends MDrawable {
    void syncGeometry();
}