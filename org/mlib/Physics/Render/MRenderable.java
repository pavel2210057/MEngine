package org.mlib.Physics.Render;

import org.mlib.Graphics.Shape.MDrawable;

public interface MRenderable extends MDrawable {
    void syncGeometry();
}