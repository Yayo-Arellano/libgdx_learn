package com.nopalsoft.learn.tutoriales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nopalsoft.learn.Assets;
import com.nopalsoft.learn.MainLearn;
import com.nopalsoft.learn.Screens;

/**
 * Tipos de cuerpos: Dinamicos, estaticos y cinematicos
 * 
 * Puedes encontrar este tutorial en mi blog: http://tutoriales.tiarsoft.com/
 * 
 * @author Gerardo Arellano
 * 
 */

public class TutorialNo6 extends Screens {

	Box2DDebugRenderer renderer;
	World oWorld;

	public TutorialNo6(MainLearn game) {
		super(game);
		Vector2 gravedad = new Vector2(0, -9.8f);
		boolean dormir = true;
		oWorld = new World(gravedad, dormir);
		renderer = new Box2DDebugRenderer();

		crearCaja();
		crearPiso();
	}

	private void crearPiso() {
		BodyDef bd = new BodyDef();
		bd.position.set(0, .5f);
		bd.type = BodyType.StaticBody;

		EdgeShape shape = new EdgeShape();
		shape.set(0, 0, WORLD_WIDTH, 1.5f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.friction = .7f;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);
		shape.dispose();
	}

	private void crearCaja() {
		BodyDef bd = new BodyDef();
		bd.position.set(7, 4);
		bd.type = BodyType.DynamicBody;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.2f, .2f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.density = 1f;
		fixDef.friction = 0f;
		fixDef.restitution = 01f;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);
		shape.dispose();
	}

	@Override
	public void update(float delta) {
		oWorld.step(delta, 8, 6);

	}

	@Override
	public void draw(float delta) {
		oCamUI.update();
		batcher.setProjectionMatrix(oCamUI.combined);

		batcher.begin();
		Assets.font.draw(batcher, "Fps:" + Gdx.graphics.getFramesPerSecond(),
				0, 20);
		batcher.end();

		oCamBox2D.update();
		renderer.render(oWorld, oCamBox2D.combined);

	}

}
