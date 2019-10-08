package org.MEngine.Graphics.Shape;

import org.MEngine.Graphics.Tools.MMat4;

public interface MDrawable {
    void update(MMat4 view);

    void draw();
}