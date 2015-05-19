package com.models.components;

import com.badlogic.ashley.core.ComponentMapper;

/**
 * Created by emily on 19/05/15.
 */
public class Components {
    public static final ComponentMapper<Enemy> ENEMY = ComponentMapper
            .getFor(Enemy.class);
    public static final ComponentMapper<Stats> STATS = ComponentMapper
            .getFor(Stats.class);
}
