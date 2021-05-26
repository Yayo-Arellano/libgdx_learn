package com.nopalsoft.learn.tutoriales.learn8;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
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

public class Learn8 extends Screens {

    World oWorld;

    Robot robot;

    Array<Body> arrBodies;

    Box2DDebugRenderer renderer;

    public Learn8(MainLearn game) {
        super(game);
        AssetsLearn8.load();

        Vector2 gravity = new Vector2(0, -15);
        oWorld = new World(gravity, true);

        arrBodies = new Array<>();


        renderer = new Box2DDebugRenderer();

        createFloor();
        createRobot();
    }

    private void createFloor() {
        BodyDef bd = new BodyDef();
        bd.position.set(0, .6f);
        bd.type = BodyType.StaticBody;

        EdgeShape shape = new EdgeShape();
        shape.set(0, 0, WORLD_WIDTH, 0);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.friction = 1f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        shape.dispose();
    }

    private void createRobot() {
        robot = new Robot(4, 5);
        BodyDef bd = new BodyDef();
        bd.position.x = robot.position.x;
        bd.position.y = robot.position.y;
        bd.type = BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Robot.WIDTH, Robot.HEIGHT);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.restitution = 0;
        fixDef.density = 15;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        oBody.setUserData(robot);

        shape.dispose();
    }


    @Override
    public void update(float delta) {
        float accelX = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            accelX = -1;
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            accelX = 1;

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            robot.duck();
        }

        if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            robot.jump();
        }

        oWorld.step(delta, 8, 6);
        oWorld.getBodies(arrBodies);
        for (Body body : arrBodies) {
            if (body.getUserData() instanceof Robot) {
                Robot obj = (Robot) body.getUserData();
                obj.update(body, delta, accelX);
            }
        }
    }

    @Override
    public void draw(float delta) {
        oCamUI.update();
        spriteBatch.setProjectionMatrix(oCamUI.combined);

        spriteBatch.begin();
        Assets.font.draw(spriteBatch, "fps:" + Gdx.graphics.getFramesPerSecond(), 0, 20);
        spriteBatch.end();

        oCamBox2D.update();

        spriteBatch.setProjectionMatrix(oCamBox2D.combined);
        spriteBatch.begin();

        drawRobot();

        spriteBatch.end();
        renderer.render(oWorld, oCamBox2D.combined);
    }

    private void drawRobot() {
        Sprite keyframe = AssetsLearn8.idle;

        if (robot.isJumping) {
            keyframe = AssetsLearn8.jump;
        } else if (robot.isFalling) {
            keyframe = AssetsLearn8.fall;
        } else if (robot.isWalking) {
            keyframe = AssetsLearn8.walk.getKeyFrame(robot.stateTime, true);
        } else if (robot.isDucking) {
            keyframe = AssetsLearn8.duck;
        }

        if (robot.velocity.x < 0) {
            keyframe.setPosition(robot.position.x + Robot.DRAW_WIDTH / 2, robot.position.y - Robot.DRAW_HEIGHT / 2 + .25f);
            keyframe.setSize(-Robot.DRAW_WIDTH, Robot.DRAW_HEIGHT);
        } else {
            keyframe.setPosition(robot.position.x - Robot.DRAW_WIDTH / 2, robot.position.y - Robot.DRAW_HEIGHT / 2 + .25f);
            keyframe.setSize(Robot.DRAW_WIDTH, Robot.DRAW_HEIGHT);
        }

        keyframe.draw(spriteBatch);
    }

    @Override
    public void dispose() {
        AssetsLearn8.dispose();
        oWorld.dispose();
        super.dispose();
    }
}
