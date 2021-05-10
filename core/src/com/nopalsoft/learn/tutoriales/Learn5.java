package com.nopalsoft.learn.tutoriales;

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
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */

public class Learn5 extends Screens {

    Box2DDebugRenderer renderer;
    World oWorld;

    // Save the bodies so later we can access the properties
    Array<Body> arrBodies;

    Array<GameObject> arrGameObjects;

    // Images of the ball and box
    TextureRegion ball, box;

    public Learn5(MainLearn game) {
        super(game);
        Vector2 gravity = new Vector2(0, -9.8f);
        oWorld = new World(gravity, true);

        renderer = new Box2DDebugRenderer();
        arrBodies = new Array<>();
        arrGameObjects = new Array<>();

        // Load the images
        ball = new TextureRegion(new Texture(Gdx.files.internal("data/ball.png")));
        box = new TextureRegion(new Texture(Gdx.files.internal("data/box.png")));

        createFloor();
        createBall();
        createBox();
    }

    private void createFloor() {
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

    private void createBall() {
        GameObject obj = new GameObject(4, 5, GameObject.BALL);

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
        arrGameObjects.add(obj);

        shape.dispose();
    }

    private void createBox() {
        GameObject obj = new GameObject(4, 5, GameObject.BOX);

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
        arrGameObjects.add(obj);

        shape.dispose();
    }

    @Override
    public void update(float delta) {

        // Every time we touch we create a new object
        if (Gdx.input.justTouched()) {
            if (MathUtils.randomBoolean())
                createBox();
            else
                createBall();
        }

        oWorld.step(delta, 8, 6);

        oWorld.getBodies(arrBodies);

        for (Body body : arrBodies) {
            if (body.getUserData() instanceof GameObject) {
                GameObject obj = (GameObject) body.getUserData();
                obj.update(body);
            }
        }
    }

    @Override
    public void draw(float delta) {
        oCamUI.update();
        spriteBatch.setProjectionMatrix(oCamUI.combined);

        spriteBatch.begin();
        Assets.font.draw(spriteBatch, "Touch the screen to create more objects", 0, 470);
        Assets.font.draw(spriteBatch, "fps:" + Gdx.graphics.getFramesPerSecond(), 0, 20);
        spriteBatch.end();

        oCamBox2D.update();

        spriteBatch.setProjectionMatrix(oCamBox2D.combined);
        spriteBatch.begin();

        drawGameObjects();

        spriteBatch.end();
        renderer.render(oWorld, oCamBox2D.combined);
    }

    private void drawGameObjects() {
        for (GameObject obj : arrGameObjects) {
            TextureRegion keyframe;

            if (obj.type == GameObject.BOX)
                keyframe = box;
            else
                keyframe = ball;

            spriteBatch.draw(keyframe,
                    obj.position.x - .15f,
                    obj.position.y - .15f,
                    .15f,
                    .15f,
                    .3f,
                    .3f,
                    1,
                    1,
                    obj.angleDeg);
        }
    }

    @Override
    public void dispose() {
        oWorld.dispose();
        super.dispose();
    }

    static public class GameObject {
        static final int BALL = 0;
        static final int BOX = 1;
        final int type;
        float angleDeg;
        Vector2 position;

        public GameObject(float x, float y, int type) {
            position = new Vector2(x, y);
            this.type = type;
        }

        public void update(Body body) {
            position.x = body.getPosition().x;
            position.y = body.getPosition().y;
            angleDeg = (float) Math.toDegrees(body.getAngle());
        }
    }

}
