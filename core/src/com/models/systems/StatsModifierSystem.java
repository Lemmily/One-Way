package com.models.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.models.components.ModifiedComponent;
import com.models.components.StatsComponent;

/**
 * Created by emily on 06/08/15.
 */
public class StatsModifierSystem extends IteratingSystem {


    public StatsModifierSystem(Family family) {
        super(Family.all(StatsComponent.class, ModifiedComponent.class).get());

    }

    @Override
    protected void processEntity(Entity pEntity, float deltaTime) {
        StatsComponent lStatsComponent = pEntity.getComponent(StatsComponent.class);
//        ModifiedComponent lModifiedComponent = pEntity.getComponent(ModifiedComponent.class);
        //check for oot modifiers.
    }
}
