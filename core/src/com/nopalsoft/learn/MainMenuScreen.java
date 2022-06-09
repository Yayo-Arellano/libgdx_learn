package com.nopalsoft.learn;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nopalsoft.learn.utils.Learn;

/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */
public class MainMenuScreen extends Screens {

    ScrollPane scroll;

    public MainMenuScreen(MainLearn game) {
        super(game);

        Table menu = new Table();
        menu.defaults().expandY().fillY();

        for (final Learn learn : Learn.values()) {
            TextButton bt = new TextButton(learn.name, Assets.txButtonStyle);
            bt.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setScreen(learn);
                }
            });

            menu.row().padTop(15).height(50);
            menu.add(bt).fillX();
        }

        scroll = new ScrollPane(menu, Assets.scrollPaneStyle);
        scroll.setSize(500, SCREEN_HEIGHT);
        scroll.setPosition(150, 0);
        stage.addActor(scroll);
    }

    private void setScreen(Learn learn) {
        try {
            Screens newScreen = learn.clazz.getConstructor(MainLearn.class).newInstance(game);
            game.setScreen(newScreen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {
    }

}
