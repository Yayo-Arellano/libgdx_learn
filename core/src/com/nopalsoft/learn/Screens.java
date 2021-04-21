package com.nopalsoft.learn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public abstract class Screens extends InputAdapter implements Screen {
	public static final float SCREEN_WIDTH = 800;
	public static final float SCREEN_HEIGHT = 480;

	public static final float WORLD_WIDTH = 8f;
	public static final float WORLD_HEIGHT = 4.8f;

	public MainLearn game;

	public OrthographicCamera oCamUI;
	public OrthographicCamera oCamBox2D;
	public SpriteBatch batcher;
	public Stage stage;

	public Screens(MainLearn game) {
		this.game = game;

		/**
		 * Creamos el stage, nos servira para agregar actores de la UI al juego
		 */
		stage = new Stage(new StretchViewport(Screens.SCREEN_WIDTH,
				Screens.SCREEN_HEIGHT));

		/**
		 * Creamos la camara para la interfaz grafica y la centramos
		 */
		oCamUI = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		oCamUI.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);

		/**
		 * Creamos la camara para la interfaz grafica y la centramos
		 */
		oCamBox2D = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
		oCamBox2D.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);

		/**
		 * Es necesario informar al stage y al InputAdapter cuando ocurren eventos de entrada como se puede ver:
		 */
		InputMultiplexer input = new InputMultiplexer(this, stage);
		Gdx.input.setInputProcessor(input);

		/**
		 * Creamos el SpriteBatch
		 */
		batcher = new SpriteBatch();

	}

	/**
	 * Funcion que se llama automaticamente normalmente 60 veces por segundo (60 FPS)
	 */
	@Override
	public void render(float delta) {

		/**
		 * Funcion donde actualizamos toda la fisica del juego
		 */
		update(delta);

		/**
		 * Actualizamos el stage
		 */
		stage.act(delta);

		/**
		 * Borra la pantalla para que podamos dibujar otra vez en ella
		 */
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/**
		 * Funcion que dibuja los objetos del juego
		 */
		draw(delta);

		/**
		 * Dibujamos los objetos del stage
		 */
		stage.draw();

	}

	public abstract void draw(float delta);

	public abstract void update(float delta);

	@Override
	public void resize(int width, int height) {
		/**
		 * Si cambia el tamano de la pantalla ajustamos el tamanao del stage
		 */
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE || keycode == Keys.BACK)
			if (this instanceof MainMenuScreen)
				Gdx.app.exit();
			else
				game.setScreen(new MainMenuScreen(game));
		return super.keyDown(keycode);
	}
}
