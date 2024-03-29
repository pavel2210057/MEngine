package org.MEngine.Graphics.Shape.Tod;

import org.MEngine.Graphics.Scene.MScene;
import org.MEngine.Graphics.Texture.MTexture;
import org.MEngine.Graphics.Tools.MRect;
import org.MEngine.Graphics.Tools.MVec2;

public class MSprite extends MRectangle {
    private MTexture texture;
    private MRect textureRect;

    public MSprite(MVec2 size) {
        super(size);
        this.texture = new MTexture();
        this.textureRect = new MRect();
    }

    public MSprite(MVec2 size, MScene scene) {
        super(size, scene);
        this.texture = new MTexture();
        this.textureRect = new MRect();
    }

    public void setTexture(MTexture texture) {
        this.texture = texture;
        getShader().setUniform("iTexture", new float[] { texture.getTexture() });
        setTextureRect(new MRect(0, 0, 1, 1));
    }

    public void setTextureRect(MRect textureRect) {
        this.textureRect = textureRect;

        getVertices().get(0).texCoord = new MVec2(textureRect.left, textureRect.top);
        getVertices().get(1).texCoord = new MVec2(textureRect.left, textureRect.top + textureRect.bottom);
        getVertices().get(2).texCoord = new MVec2(textureRect.left + textureRect.right, textureRect.top);
        getVertices().get(3).texCoord = new MVec2(textureRect.left, textureRect.top + textureRect.bottom);
        getVertices().get(4).texCoord = new MVec2(textureRect.left + textureRect.right, textureRect.top);
        getVertices().get(5).texCoord = new MVec2(textureRect.left + textureRect.right, textureRect.top + textureRect.bottom);

        if (isLoaded())
            updateTexCoord();
    }

    public MTexture getTexture() {
        return this.texture;
    }

    public MRect getTextureRect() {
        return this.textureRect;
    }

    @Override
    public void draw() {
        MTexture.bind(this.texture.getTexture());
        super.draw();
        MTexture.bind(0);
    }

    @Override
    public boolean compare(MShape right) {
        return
                super.compare(right) &&
                this.texture.getTexture() == ((MSprite)right).getTexture().getTexture();
    }
}