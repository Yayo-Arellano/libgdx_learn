package com.nopalsoft.learn;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nopalsoft.learn.tutoriales.Learn1;
import com.nopalsoft.learn.utils.Learn;

/**
 * Puedes encontrar este tutorial en mi blog: http://tutoriales.tiarsoft.com/
 *
 * @author Gerardo Arellano
 */


public class MainMenuScreen extends Screens {

    ScrollPane scroll;

    public MainMenuScreen(MainLearn game) {
        super(game);

        Table menu = new Table();
        menu.setFillParent(true);
        menu.defaults().uniform().fillY();

        for (final Learn tutorial : Learn.values()) {
            TextButton bt = new TextButton(tutorial.name, Assets.txButtonStyle);
            bt.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MainMenuScreen.this.game.setScreen(getScreen(tutorial));
                }
            });

            menu.row().padTop(20).height(50);
            menu.add(bt);
        }

        scroll = new ScrollPane(menu, Assets.scrollPaneStyle);
        scroll.setSize(500, SCREEN_HEIGHT);
        scroll.setPosition(150, 0);
        stage.addActor(scroll);
    }

    private Screens getScreen(Learn learn) {
        switch (learn) {
            default:
                return new Learn1(game);
        }
    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {
    }

}
