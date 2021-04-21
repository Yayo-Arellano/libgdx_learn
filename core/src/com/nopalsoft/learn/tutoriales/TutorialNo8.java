package com.nopalsoft.learn.tutoriales;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nopalsoft.learn.Assets;
import com.nopalsoft.learn.MainLearn;
import com.nopalsoft.learn.Screens;

/**
 * Cuerpos y Sprites (Imagenes)
 * 
 * Puedes encontrar este tutorial en mi blog: http://tutoriales.tiarsoft.com/
 * 
 * @author Gerardo Arellano
 * 
 */

public class TutorialNo8 extends Screens {

	Box2DDebugRenderer renderer;
	World oWorld;

	/**
	 * Esta lista va almacenar todos los cuerpos que creemos en nuestro mundo
	 */
	Array<Body> arrBodies;

	/**
	 * Esta lista va almacenar todos los objetos figura que creemos
	 */
	Array<Figura> arrFiguras;

	/**
	 * Imagenes de la pelota y la caja.
	 */
	TextureRegion pelota, caja;

	public TutorialNo8(MainLearn game) {
		super(game);
		Vector2 gravedad = new Vector2(0, -9.8f);
		boolean dormir = true;
		oWorld = new World(gravedad, dormir);
		renderer = new Box2DDebugRenderer();
		arrBodies = new Array<Body>();
		arrFiguras = new Array<Figura>();

		/**
		 * Cargamos las imagenes
		 */
		pelota = new TextureRegion(new Texture(
				Gdx.files.internal("data/pelota.png")));
		caja = new TextureRegion(new Texture(
				Gdx.files.internal("data/caja.png")));

		crearPiso();
		crearPelota();
		crearCaja();
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

	private void crearPelota() {
		Figura obj = new Figura(4, 5, Figura.TIPO_PELOTA);

		BodyDef bd = new BodyDef();
		bd.position.x = obj.position.x;
		bd.position.y = obj.position.y;
		bd.type = BodyType.DynamicBody;

		CircleShape shape = new CircleShape();
		shape.setRadius(.15f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.density = 15;
		fixDef.friction = .5f;
		fixDef.restitution = .5f;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);
		oBody.setUserData(obj);
		arrFiguras.add(obj);

		shape.dispose();
	}

	private void crearCaja() {
		Figura obj = new Figura(4, 5, Figura.TIPO_CAJA);

		BodyDef bd = new BodyDef();
		bd.position.x = obj.position.x;
		bd.position.y = obj.position.y;
		bd.type = BodyType.DynamicBody;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.15f, .15f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.density = 15;
		fixDef.friction = .5f;
		fixDef.restitution = .1f;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);
		oBody.setUserData(obj);
		arrFiguras.add(obj);

		shape.dispose();
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.justTouched()) {
			if (MathUtils.randomBoolean())
				crearCaja();
			else
				crearPelota();
		}

		oWorld.step(delta, 8, 6);

		oWorld.getBodies(arrBodies);

		Iterator<Body> i = arrBodies.iterator();
		while (i.hasNext()) {
			Body body = i.next();
			if (body.getUserData() instanceof Figura) {
				Figura obj = (Figura) body.getUserData();
				obj.update(body);

			}

		}

	}

	@Override
	public void draw(float delta) {
		oCamUI.update();
		batcher.setProjectionMatrix(oCamUI.combined);

		batcher.begin();
		Assets.font.draw(batcher, "Toca la pantalla para crear mas figuras", 0,
				470);

		Assets.font.draw(batcher, "Fps:" + Gdx.graphics.getFramesPerSecond(),
				0, 20);
		batcher.end();

		oCamBox2D.update();

		batcher.setProjectionMatrix(oCamBox2D.combined);
		batcher.begin();

		drawFiguras();

		batcher.end();
		renderer.render(oWorld, oCamBox2D.combined);

	}

	private void drawFiguras() {
		Iterator<Figura> i = arrFiguras.iterator();
		while (i.hasNext()) {
			Figura obj = i.next();
			TextureRegion keyframe;

			if (obj.tipo == Figura.TIPO_CAJA)
				keyframe = caja;
			else
				keyframe = pelota;

			batcher.draw(keyframe, obj.position.x - .15f,
					obj.position.y - .15f, .15f, .15f, .3f, .3f, 1, 1,
					obj.angleDeg);

		}

	}

	public class Figura {
		static final int TIPO_PELOTA = 0;
		static final int TIPO_CAJA = 1;
		final int tipo;
		Vector2 position;
		float angleDeg;

		public Figura(float x, float y, int tipo) {
			position = new Vector2(x, y);
			this.tipo = tipo;

		}

		public void update(Body body) {
			position.x = body.getPosition().x;
			position.y = body.getPosition().y;
			angleDeg = (float) Math.toDegrees(body.getAngle());
		}
	}

}
