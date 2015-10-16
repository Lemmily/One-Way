package com.lemmily.game.models.components;

import com.badlogic.ashley.core.ComponentMapper;

/**
 * Created by emily on 19/05/15.
 */
public class Components {
    public static final ComponentMapper<EnemyComponent> ENEMY = ComponentMapper
            .getFor(EnemyComponent.class);
    public static final ComponentMapper<StatsComponent> STATS = ComponentMapper
            .getFor(StatsComponent.class);
    public static final ComponentMapper<PlayerComponent> PLAYER = ComponentMapper
            .getFor(PlayerComponent.class);
    public static final ComponentMapper<EquipmentComponent> EQUIPMENT = ComponentMapper
            .getFor(EquipmentComponent.class);
}
