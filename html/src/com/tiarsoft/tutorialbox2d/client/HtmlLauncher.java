package com.tiarsoft.tutorialbox2d.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.tiarsoft.tutorialbox2d.MainBox2d;

public class HtmlLauncher extends GwtApplication {

	@Override
	public GwtApplicationConfiguration getConfig() {
		return new GwtApplicationConfiguration(650, 390);
	}

	@Override
	public ApplicationListener getApplicationListener() {
		return new MainBox2d();
	}
}