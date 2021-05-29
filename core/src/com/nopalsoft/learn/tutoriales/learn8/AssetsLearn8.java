package com.nopalsoft.learn.tutoriales.learn8;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */
public class AssetsLearn8 {

    static Sprite duck;
    static Sprite fall;
    static Sprite idle;
    static Sprite jump;

    static Animation<Sprite> walk;
    static TextureAtlas atlas;

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("data/learn8/learn8.txt"));

        duck = atlas.createSprite("character_robot_duck");
        fall = atlas.createSprite("character_robot_fall");
        idle = atlas.createSprite("character_robot_idle");
        jump = atlas.createSprite("character_robot_jump");

        walk = new Animation<>(
                Robot.WALK_FRAME_DURATION,
                atlas.createSprite("character_robot_walk0"),
                atlas.createSprite("character_robot_walk1"),
                atlas.createSprite("character_robot_walk2"),
                atlas.createSprite("character_robot_walk3"),
                atlas.createSprite("character_robot_walk4"),
                atlas.createSprite("character_robot_walk5"),
                atlas.createSprite("character_robot_walk6"),
                atlas.createSprite("character_robot_walk7"));
    }

    public static void dispose() {
        atlas.dispose();
    }
}

