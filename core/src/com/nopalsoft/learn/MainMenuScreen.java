package com.nopalsoft.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nopalsoft.learn.tutoriales.TutorialNo4;
import com.nopalsoft.learn.tutoriales.TutorialNo5;
import com.nopalsoft.learn.tutoriales.TutorialNo6;
import com.nopalsoft.learn.tutoriales.TutorialNo7;
import com.nopalsoft.learn.tutoriales.TutorialNo8;

/**
 * Puedes encontrar este tutorial en mi blog: http://tutoriales.tiarsoft.com/
 * 
 * @author Gerardo Arellano
 * 
 */

@SuppressWarnings("unchecked")
public class MainMenuScreen extends Screens {

	public static final List<Class<? extends Screens>> tests = new ArrayList<Class<? extends Screens>>(
			Arrays.asList(//
					TutorialNo4.class,//
					TutorialNo5.class,//
					TutorialNo6.class,//
					TutorialNo7.class,//
					TutorialNo8.class//
			));

	ScrollPane scroll;

	public MainMenuScreen(MainLearn game) {
		super(game);

		Table menu = new Table();
		menu.setFillParent(true);
		menu.defaults().uniform().fillY();
//		menu.debug();

		Iterator<Class<? extends Screens>> i = tests.iterator();
		int tutorialNo = 4;
		while (i.hasNext()) {
			final Class<? extends Screens> clase = i.next();
			TextButton bt = new TextButton("Tutorial " + tutorialNo,
					Assets.txButtonStyle);
			bt.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					newScreen(clase);
				}
			});

			menu.row().padTop(20).height(50).width(180);
			menu.add(bt);
			tutorialNo++;

		}

		scroll = new ScrollPane(menu, Assets.scrollPaneStyle);
		scroll.setSize(200, SCREEN_HEIGHT);
		scroll.setPosition(300, 0);
		stage.addActor(scroll);

	}

	private void newScreen(Class<? extends Screens> screenClass) {

		// try {
		// game.setScreen(screenClass.getConstructor(MainBox2d.class)
		// .newInstance(game));
		// }
		// catch (Exception e) {
		// e.printStackTrace();
		// }

		if (screenClass == TutorialNo4.class)
			game.setScreen(new TutorialNo4(game));
		else if (screenClass == TutorialNo5.class)
			game.setScreen(new TutorialNo5(game));
		else if (screenClass == TutorialNo6.class)
			game.setScreen(new TutorialNo6(game));
		else if (screenClass == TutorialNo7.class)
			game.setScreen(new TutorialNo7(game));
		else if (screenClass == TutorialNo8.class)
			game.setScreen(new TutorialNo8(game));

	}

	@Override
	public void draw(float delta) {
//		 Table.drawDebug(stage);
	}

	@Override
	public void update(float delta) {

	}

}
