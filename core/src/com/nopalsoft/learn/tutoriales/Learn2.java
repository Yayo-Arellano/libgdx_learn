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
import com.badlogic.gdx.physics.box2d.PolygonShape;
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
public class Learn2 extends Screens {

    Box2DDebugRenderer renderer;
    World oWorld;

    public Learn2(MainLearn game) {
        super(game);
        Vector2 gravity = new Vector2(0, -9.8f);
        oWorld = new World(gravity, true);

        renderer = new Box2DDebugRenderer();
        createDynamic();
        createStatic();
        createKinematic();
    }

    private void createStatic() {
        BodyDef bd = new BodyDef();
        bd.position.set(0, .5f);
        bd.type = BodyType.StaticBody;

        EdgeShape shape = new EdgeShape();
        shape.set(0, 0, WORLD_WIDTH, 0);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);

        shape.dispose();

    }

    private void createKinematic() {
        BodyDef bd = new BodyDef();
        bd.position.set(4, 1.5f);
        bd.type = BodyType.KinematicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.1f, .75f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);

        shape.dispose();

        // The kinematic body will rotate counterclockwise
        oBody.setAngularVelocity((float) Math.toRadians(360));
    }

    private void createDynamic() {
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
