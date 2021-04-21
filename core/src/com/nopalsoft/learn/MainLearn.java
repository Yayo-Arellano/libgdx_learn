package com.nopalsoft.learn;

import com.badlogic.gdx.Game;

public class MainLearn extends Game {

	@Override
	public void create() {
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}

}
