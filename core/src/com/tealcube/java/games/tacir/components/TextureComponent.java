package com.tealcube.java.games.tacir.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TextureComponent extends Component implements Poolable {

    private TextureRegion textureRegion = null;

    @Override
    public void reset() {
        if (hasTextureRegion()) {
            textureRegion.getTexture().dispose();
        }
        textureRegion = null;
    }

    public boolean hasTextureRegion() {
        return textureRegion != null;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

}
