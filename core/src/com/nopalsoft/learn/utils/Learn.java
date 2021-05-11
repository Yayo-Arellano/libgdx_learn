package com.nopalsoft.learn.utils;

/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */

public enum Learn {
    LEARN_1("The world, bodies, figures and fixtures"),
    LEARN_2("Body Types: dynamic, static & kinematic"),
    LEARN_3("Friction, density & restitution"),
    LEARN_4("Forces, impulses & linear velocity"),
    LEARN_5("Bodies & sprites"),
    LEARN_6("Collisions"),
    LEARN_7("Collisions with animated sprites");

    public final String name;

    Learn(String label) {
        this.name = label;
    }
}
