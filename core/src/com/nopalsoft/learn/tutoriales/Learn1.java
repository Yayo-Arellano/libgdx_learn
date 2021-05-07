package com.nopalsoft.learn.tutoriales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.nopalsoft.learn.Assets;
import com.nopalsoft.learn.MainLearn;
import com.nopalsoft.learn.Screens;

/**
 * El mundo, cuerpos, figuras y fixturas
 * 
 * Puedes encontrar este tutorial en mi blog: http://tutoriales.tiarsoft.com/
 * 
 * @author Gerardo Arellano
 * 
 */

public class Learn1 extends Screens {

	Box2DDebugRenderer renderer;
	World oWorld;

	public Learn1(MainLearn game) {
		super(game);
		Vector2 gravedad = new Vector2(0, -9.8f);
		boolean dormir = true;
		oWorld = new World(gravedad, dormir);
		renderer = new Box2DDebugRenderer();

		crearPelota();
	}

	private void crearPelota() {
		BodyDef bd = new BodyDef();
		bd.position.set(4, 4.5f);
		bd.type = BodyType.DynamicBody;

		CircleShape shape = new CircleShape();
		shape.setRadius(.25f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);
	}

	@Override
	public void update(float delta) {
		oWorld.step(delta, 8, 6);

	}

	@Override
	public void draw(float delta) {
		oCamUI.update();
		spriteBatch.setProjectionMatrix(oCamUI.combined);

		spriteBatch.begin();
		Assets.font.draw(spriteBatch, "Fps:" + Gdx.graphics.getFramesPerSecond(),
				0, 20);
		spriteBatch.end();

		oCamBox2D.update();
		renderer.render(oWorld, oCamBox2D.combined);

	}

}
