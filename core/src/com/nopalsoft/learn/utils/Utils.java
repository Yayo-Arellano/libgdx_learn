package com.nopalsoft.learn.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */
public class Utils {

    static public TextureRegion getRegion(String path) {
        return new TextureRegion(new Texture(Gdx.files.internal(path)));
    }
}
