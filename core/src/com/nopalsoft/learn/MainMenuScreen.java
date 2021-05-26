package com.nopalsoft.learn;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nopalsoft.learn.tutoriales.*;
import com.nopalsoft.learn.tutoriales.learn8.Learn8;
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

        for (final Learn tutorial : Learn.values()) {
            TextButton bt = new TextButton(tutorial.name, Assets.txButtonStyle);
            bt.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MainMenuScreen.this.game.setScreen(getScreen(tutorial));
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

    private Screens getScreen(Learn learn) {
        switch (learn) {
            default:
                return new Learn1(game);
            case LEARN_2:
                return new Learn2(game);
            case LEARN_3:
                return new Learn3(game);
            case LEARN_4:
                return new Learn4(game);
            case LEARN_5:
                return new Learn5(game);
            case LEARN_6:
                return new Learn6(game);
            case LEARN_7:
                return new Learn7(game);
            case LEARN_8:
                return new Learn8(game);
        }
    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {
    }

}
