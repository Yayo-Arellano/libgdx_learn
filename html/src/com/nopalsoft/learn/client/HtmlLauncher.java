package com.nopalsoft.learn.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.nopalsoft.learn.MainLearn;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // 800 x 480 = 5:3 aspect ratio.
        // width = 5 / 3 * height; <<-- To calculate the width and keep aspect ratio given height
        int height = (int) (com.google.gwt.user.client.Window.getClientHeight() * 0.75f);
        int width = (int) (5f / 3f * height);

        return new GwtApplicationConfiguration(width, height);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new MainLearn();
    }
}