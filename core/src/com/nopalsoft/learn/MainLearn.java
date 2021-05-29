package com.nopalsoft.learn;

import com.badlogic.gdx.Game;
import com.nopalsoft.learn.tutoriales.learn8.Learn8;

/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */
public class MainLearn extends Game {

    @Override
    public void create() {
        Assets.load();
        setScreen(new MainMenuScreen(this));
    }

}
