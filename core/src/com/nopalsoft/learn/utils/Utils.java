package com.nopalsoft.learn.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Utils {

    static public TextureRegion getRegion(String path) {
        return new TextureRegion(new Texture(Gdx.files.internal(path)));
    }
}
