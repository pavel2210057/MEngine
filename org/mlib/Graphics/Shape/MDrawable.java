package org.mlib.Graphics.Shape;

import org.mlib.Graphics.Tools.MMat4;

public interface MDrawable {
    void update(MMat4 view);

    void draw();
}