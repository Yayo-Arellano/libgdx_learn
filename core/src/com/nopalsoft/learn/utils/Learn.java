package com.nopalsoft.learn.utils;

import com.nopalsoft.learn.Screens;
import com.nopalsoft.learn.tutoriales.*;
import com.nopalsoft.learn.tutoriales.learn8.Learn8;

/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */
public enum Learn {
    LEARN_1("The world, bodies, figures and fixtures", Learn1.class),
    LEARN_2("Body Types: dynamic, static & kinematic", Learn2.class),
    LEARN_3("Friction, density & restitution", Learn3.class),
    LEARN_4("Forces, impulses & linear velocity", Learn4.class),
    LEARN_5("Bodies & sprites", Learn5.class),
    LEARN_6("Collisions", Learn6.class),
    LEARN_7("Collisions with animated sprites", Learn7.class),
    LEARN_8("Texture packer & animated sprites", Learn8.class),
    LEARN_9("Support multiple localizations", Learn9.class);

    public final String name;
    public final Class<? extends Screens> clazz;

    Learn(String label, Class<? extends Screens> clazz) {
        this.name = label;
        this.clazz = clazz;
    }
}
