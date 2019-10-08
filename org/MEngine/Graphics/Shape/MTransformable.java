package org.MEngine.Graphics.Shape;

import org.MEngine.Graphics.Tools.MVec2;

public interface MTransformable {
    void setPosition(MVec2 position);

    void move(MVec2 offset);

    void scale(MVec2 factor);

    void rotate(float degree);

    void setRotation(float degree);

    MVec2 getPosition();

    MVec2 getSize();

    float getRotation();
}