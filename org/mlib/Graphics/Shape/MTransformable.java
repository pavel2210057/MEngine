package org.mlib.Graphics.Shape;

import org.mlib.Graphics.Tools.MVec2;

public interface MTransformable {
    void setPosition(MVec2 position);

    void move(MVec2 offset);

    void scale(MVec2 factor);

    MVec2 getPosition();

    MVec2 getSize();
}