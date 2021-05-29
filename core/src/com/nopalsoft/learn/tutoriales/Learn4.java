package com.nopalsoft.learn.tutoriales;

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
public class Learn4 extends Screens {

    Box2DDebugRenderer renderer;
    World oWorld;

    Body[] arrBalls;

    // Same force will be applied to the 3 balls. It is positive in Y so balls should move upwards
    Vector2 speed = new Vector2(0, 8);

    public Learn4(MainLearn game) {
        super(game);
        Vector2 gravity = new Vector2(0, -9.8f);
        oWorld = new World(gravity, true);

        renderer = new Box2DDebugRenderer();
        arrBalls = new Body[3];

        createFloor();

        arrBalls[0] = createBall(2.5f, .8f); // Left Ball
        arrBalls[1] = createBall(4.0f, .8f); // Center Ball
        arrBalls[2] = createBall(5.5f, .8f); // Right Ball

        // Apply the force to the left ball
        arrBalls[0].applyForceToCenter(speed, true);

        // Apply the impulse to the center ball
        arrBalls[1].applyLinearImpulse(speed, arrBalls[1].getWorldCenter(), true);

        // Set the linear velocity to the right ball
        arrBalls[2].setLinearVelocity(speed);

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

    private Body createBall(float positionX, float positionY) {
        BodyDef bd = new BodyDef();
        bd.position.set(positionX, positionY);
        bd.type = BodyType.DynamicBody;

        CircleShape shape = new CircleShape();
        shape.setRadius(.25f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 11;
        fixDef.friction = .5f;
        fixDef.restitution = .5f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        shape.dispose();
        return oBody;
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
        Assets.font.draw(spriteBatch, "fps:" + Gdx.graphics.getFramesPerSecond(), 0, 20);
        spriteBatch.end();

        oCamBox2D.update();
        renderer.render(oWorld, oCamBox2D.combined);
    }

    @Override
    public void dispose() {
        oWorld.dispose();
        super.dispose();
    }
}
