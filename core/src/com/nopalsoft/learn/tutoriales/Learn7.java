package com.nopalsoft.learn.tutoriales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nopalsoft.learn.Assets;
import com.nopalsoft.learn.MainLearn;
import com.nopalsoft.learn.Screens;
import com.nopalsoft.learn.utils.Utils;

/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */

public class Learn7 extends Screens {

    Box2DDebugRenderer renderer;
    World oWorld;

    // Save the bodies so later we can access the properties
    Array<Body> arrBodies;

    Array<GameObject> arrGameObjects;

    // Images of the ball and box
    TextureRegion ball, box;
    Animation<TextureRegion> explosion;


    public Learn7(MainLearn game) {
        super(game);
        Vector2 gravity = new Vector2(0, -9.8f);
        oWorld = new World(gravity, true);
        oWorld.setContactListener(new CollisionListener());

        renderer = new Box2DDebugRenderer();
        arrBodies = new Array<>();
        arrGameObjects = new Array<>();

        // Load the images
        ball = Utils.getRegion("data/ball.png");
        box = Utils.getRegion("data/box.png");


        TextureRegion exp1 = Utils.getRegion("data/explosion/explosion1.png");
        TextureRegion exp2 = Utils.getRegion("data/explosion/explosion2.png");
        TextureRegion exp3 = Utils.getRegion("data/explosion/explosion3.png");
        TextureRegion exp4 = Utils.getRegion("data/explosion/explosion4.png");
        TextureRegion exp5 = Utils.getRegion("data/explosion/explosion5.png");
        TextureRegion exp6 = Utils.getRegion("data/explosion/explosion6.png");
        TextureRegion exp7 = Utils.getRegion("data/explosion/explosion7.png");
        TextureRegion exp8 = Utils.getRegion("data/explosion/explosion8.png");
        TextureRegion exp9 = Utils.getRegion("data/explosion/explosion9.png");
        TextureRegion exp10 = Utils.getRegion("data/explosion/explosion10.png");
        TextureRegion exp11 = Utils.getRegion("data/explosion/explosion11.png");
        TextureRegion exp12 = Utils.getRegion("data/explosion/explosion12.png");
        TextureRegion exp13 = Utils.getRegion("data/explosion/explosion13.png");
        TextureRegion exp14 = Utils.getRegion("data/explosion/explosion14.png");
        TextureRegion exp15 = Utils.getRegion("data/explosion/explosion15.png");
        TextureRegion exp16 = Utils.getRegion("data/explosion/explosion16.png");
        TextureRegion exp17 = Utils.getRegion("data/explosion/explosion17.png");
        TextureRegion exp18 = Utils.getRegion("data/explosion/explosion18.png");
        TextureRegion exp19 = Utils.getRegion("data/explosion/explosion19.png");

        explosion = new Animation<>(0.05f, exp1, exp2, exp3,
                exp4, exp5, exp6, exp7, exp8, exp9,
                exp10, exp11, exp12, exp13, exp14,
                exp15, exp16, exp17, exp18, exp19);


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
        GameObject obj = new GameObject((float) Math.random() * WORLD_WIDTH, 5, GameObject.BALL);

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
        GameObject obj = new GameObject((float) Math.random() * WORLD_WIDTH, 5, GameObject.BOX);

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

        // Eliminate objects that have been hit
        for (Body body : arrBodies) {

            // If the world is locked do not do anything to this body
            if (oWorld.isLocked()) continue;

            if (body.getUserData() instanceof GameObject) {
                GameObject obj = (GameObject) body.getUserData();
                if (obj.state == GameObject.STATE_REMOVE) {
                    arrGameObjects.removeValue(obj, true);
                    oWorld.destroyBody(body);
                }
            }
        }

        oWorld.getBodies(arrBodies);
        for (Body body : arrBodies) {
            if (body.getUserData() instanceof GameObject) {
                GameObject obj = (GameObject) body.getUserData();
                obj.update(body, delta);
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

//        oCamBox2D.zoom = .35f;
//        oCamBox2D.position.y = 1.2f;
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

            if (obj.state == GameObject.STATE_NORMAL) {
                if (obj.type == GameObject.BOX)
                    keyframe = box;
                else
                    keyframe = ball;

                spriteBatch.draw(keyframe, obj.position.x - .15f, obj.position.y - .15f, .15f, .15f,
                        .3f, .3f, 1, 1, obj.angleDeg);
            }


            if (obj.state == GameObject.STATE_EXPLODE) {
                spriteBatch.draw(explosion.getKeyFrame(obj.stateTime), obj.position.x - .15f, obj.position.y - .15f,
                        .15f, .15f, .3f, .3f, 1, 1, obj.angleDeg);
            }
        }
    }

    @Override
    public void dispose() {
        oWorld.dispose();
        super.dispose();
    }

    static private class GameObject {
        static final int BALL = 0;
        static final int BOX = 1;

        static final int STATE_NORMAL = 0;
        static final int STATE_EXPLODE = 1;
        static final int STATE_REMOVE = 2;

        static final float EXPLOSION_DURATION = 0.95f;

        int state;
        float stateTime = 0;


        final int type;
        float angleDeg;
        Vector2 position;

        public GameObject(float x, float y, int type) {
            position = new Vector2(x, y);
            state = STATE_NORMAL;
            this.type = type;
        }

        public void update(Body body, float delta) {
            if (state == STATE_NORMAL) {
                position.x = body.getPosition().x;
                position.y = body.getPosition().y;
                angleDeg = (float) Math.toDegrees(body.getAngle());
            } else if (state == STATE_EXPLODE) {
                if (stateTime >= EXPLOSION_DURATION) {
                    state = STATE_REMOVE;
                    stateTime = 0;
                }
            }
            stateTime += delta;
        }

        public void hit() {
            if (state == STATE_NORMAL) {
                state = STATE_EXPLODE;
                stateTime = 0;
            }
        }
    }

    static private class CollisionListener implements ContactListener {

        @Override
        public void beginContact(Contact contact) {
            Body bodyA = contact.getFixtureA().getBody();
            Body bodyB = contact.getFixtureB().getBody();

            if (bodyA.getUserData() instanceof GameObject && bodyB.getUserData() instanceof GameObject) {
                GameObject gameObjectA = (GameObject) bodyA.getUserData();
                GameObject gameObjectB = (GameObject) bodyB.getUserData();

                if (gameObjectA.type == gameObjectB.type) {
                    if (gameObjectA.state == GameObject.STATE_NORMAL && gameObjectB.state == GameObject.STATE_NORMAL) {
                        gameObjectA.hit();
                        gameObjectB.hit();
                    }
                }
            }
        }

        @Override
        public void endContact(Contact contact) {

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {
            Body bodyA = contact.getFixtureA().getBody();
            Body bodyB = contact.getFixtureB().getBody();

            if (bodyA.getUserData() instanceof GameObject && bodyB.getUserData() instanceof GameObject) {
                GameObject gameObjectA = (GameObject) bodyA.getUserData();
                GameObject gameObjectB = (GameObject) bodyB.getUserData();

                if (gameObjectA.state != GameObject.STATE_NORMAL || gameObjectB.state != GameObject.STATE_NORMAL)
                    contact.setEnabled(false);
                else
                    contact.setEnabled(true);
            }

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }

    }


}
