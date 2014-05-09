package com.tiarsoft.tutorialbox2d.tutoriales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.tiarsoft.tutorialbox2d.Assets;
import com.tiarsoft.tutorialbox2d.MainBox2d;
import com.tiarsoft.tutorialbox2d.Screens;

/**
 * Tipos de cuerpos: Dinamicos, estaticos y cinematicos
 * 
 * Puedes encontrar este tutorial en mi blog: http://tutoriales.tiarsoft.com/
 * 
 * @author Gerardo Arellano
 * 
 */

public class TutorialNo7 extends Screens {

	Box2DDebugRenderer renderer;
	World oWorld;

	/**
	 * En este arreglo se van almacenar los cuerpos de las 3 pelotas
	 */
	Body[] arrPelotas;

	/**
	 * Fuerza que aplicaremos a las 3 pelotas, es positiva en Y por lo que las pelotas deberan moverse hacia arriba
	 */
	Vector2 velocidad = new Vector2(0, 8);

	public TutorialNo7(MainBox2d game) {
		super(game);
		Vector2 gravedad = new Vector2(0, -9.8f);
		boolean dormir = true;
		oWorld = new World(gravedad, dormir);
		renderer = new Box2DDebugRenderer();
		arrPelotas = new Body[3];

		crearPiso();

		crearPelotaIzquierda();
		crearPelotaCentro();
		crearPelotaDerecha();

		/**
		 * Aplicamos una fuerza a la pelota de la izquierda
		 */
		arrPelotas[0].applyForceToCenter(velocidad, true);

		/**
		 * Aplicamos un impulso a la pelota del centro
		 */
		arrPelotas[1].applyLinearImpulse(velocidad,
				arrPelotas[1].getWorldCenter(), true);

		/**
		 * Aplicamos la velocidad linear a la pelota de la derecha
		 */
		arrPelotas[2].setLinearVelocity(velocidad);

	}

	/**
	 * Creamos el piso
	 */
	private void crearPiso() {
		BodyDef bd = new BodyDef();
		bd.position.set(0, .6f);
		bd.type = BodyType.StaticBody;

		EdgeShape shape = new EdgeShape();
		shape.set(0, 0, WORLD_WIDTH, 0);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.friction = .7f;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);
		shape.dispose();
	}

	/**
	 * Creamos la pelota de la izquierda en la posicion x= 2.5f y y=.8f con un radio de .25mts y una densidad de 15km/s^2
	 */
	private void crearPelotaIzquierda() {
		BodyDef bd = new BodyDef();
		bd.position.set(2.5f, .8f);
		bd.type = BodyType.DynamicBody;

		CircleShape shape = new CircleShape();
		shape.setRadius(.25f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.density = 15;
		fixDef.friction = .5f;
		fixDef.restitution = .5f;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);
		arrPelotas[0] = oBody;
		shape.dispose();
	}

	/**
	 * Creamos la pelota del centro en la posicion x= 4f y y=.8f con un radio de .25mts y una densidad de 15km/s^2
	 */
	private void crearPelotaCentro() {
		BodyDef bd = new BodyDef();
		bd.position.set(4, .8f);
		bd.type = BodyType.DynamicBody;

		CircleShape shape = new CircleShape();
		shape.setRadius(.25f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.density = 15;
		fixDef.friction = .5f;
		fixDef.restitution = .5f;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);
		arrPelotas[1] = oBody;
		shape.dispose();
	}

	/**
	 * Creamos la pelota de la derecha en la posicion x= 5.5f y y=.8f con un radio de .25mts y una densidad de 15km/s^2
	 */
	private void crearPelotaDerecha() {
		BodyDef bd = new BodyDef();
		bd.position.set(5.5f, .8f);
		bd.type = BodyType.DynamicBody;

		CircleShape shape = new CircleShape();
		shape.setRadius(.25f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.density = 15;
		fixDef.friction = .5f;
		fixDef.restitution = .5f;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);
		arrPelotas[2] = oBody;
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
