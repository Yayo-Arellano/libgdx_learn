package com.nopalsoft.learn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class Assets {

    public static BitmapFont font;

    public static TextButtonStyle txButtonStyle;
    public static ScrollPaneStyle scrollPaneStyle;

    public static void load() {
        font = new BitmapFont();

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/ui.txt"));

        NinePatchDrawable bt = new NinePatchDrawable(atlas.createPatch("bt"));
        NinePatchDrawable btDown = new NinePatchDrawable(atlas.createPatch("btDown"));

        txButtonStyle = new TextButtonStyle(bt, btDown, null, font);

        NinePatchDrawable knob = new NinePatchDrawable(atlas.createPatch("scroll"));
        scrollPaneStyle = new ScrollPaneStyle(null, knob, knob, knob, knob);
    }

}
