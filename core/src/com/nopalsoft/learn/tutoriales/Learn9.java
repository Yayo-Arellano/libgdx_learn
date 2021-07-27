package com.nopalsoft.learn.tutoriales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.nopalsoft.learn.Assets;
import com.nopalsoft.learn.MainLearn;
import com.nopalsoft.learn.Screens;

import java.util.Locale;

/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */
public class Learn9 extends Screens {


    public Learn9(MainLearn game) {
        super(game);
        buildUI(Locale.ENGLISH);
    }

    private void buildUI(Locale locale) {
        stage.clear();

        I18NBundle i18n = I18NBundle.createBundle(Gdx.files.internal("data/learn9/strings"), locale);

        TextButton btEnglish = new TextButton("English", Assets.txButtonStyle);
        TextButton btSpanish = new TextButton("Español", Assets.txButtonStyle);

        btEnglish.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buildUI(Locale.ENGLISH);
            }
        });

        btSpanish.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buildUI(new Locale("es"));
            }
        });

        Table menu = new Table();
        menu.defaults();

        menu.row().colspan(2).align(Align.left);
        menu.add(new Label(i18n.get("hello_world"), Assets.labelStyle));

        menu.row().colspan(2).align(Align.left);
        menu.add(new Label(i18n.format("formatted_number", 100), Assets.labelStyle));

        menu.row().colspan(2).align(Align.left);
        menu.add(new Label(i18n.get("text"), Assets.labelStyle));

        menu.row().padTop(15).height(50);

        menu.add(btEnglish).minWidth(100);
        menu.add(btSpanish).padLeft(10).minWidth(100);


        menu.setFillParent(true);
        stage.addActor(menu);
//        stage.setDebugAll(true);

    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {
    }

}
