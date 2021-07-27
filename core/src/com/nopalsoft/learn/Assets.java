package com.nopalsoft.learn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */
public class Assets {

    public static BitmapFont font;

    public static TextButtonStyle txButtonStyle;
    public static Label.LabelStyle labelStyle;
    public static ScrollPaneStyle scrollPaneStyle;

    public static void load() {
        font = new BitmapFont();

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/ui.txt"));

        txButtonStyle = new TextButtonStyle(new NinePatchDrawable(
                atlas.createPatch("bt")), new NinePatchDrawable(
                atlas.createPatch("btDown")), null, font);

        labelStyle = new Label.LabelStyle(Assets.font, Color.GREEN);

        NinePatchDrawable knob = new NinePatchDrawable(
                atlas.createPatch("scroll"));
        scrollPaneStyle = new ScrollPaneStyle(null, knob, knob, knob, knob);
    }

}
