package com.tiarsoft.tutorialbox2d;

import com.badlogic.gdx.Game;

public class MainBox2d extends Game {

	@Override
	public void create() {
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}

}
