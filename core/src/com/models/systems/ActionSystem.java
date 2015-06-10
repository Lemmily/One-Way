package com.models.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.models.PlayerController;
import com.models.components.EnemyComponent;
import com.models.components.StatsComponent;
import com.models.entities.Monster;

import java.util.Comparator;

/**
 * Created by emily on 19/05/15.
 */
public class ActionSystem extends SortedIteratingSystem {


    private static Family FAMILY = Family.all(EnemyComponent.class, StatsComponent.class).get();

    public ActionSystem( Comparator<Entity> comparator) {
        super(FAMILY, comparator);
    }

    @Override
    public void update(float deltaTime) {
        //if the player has moved
        if (PlayerController.get().playerMoved()) {
            super.update(deltaTime);
            PlayerController.get().setPlayerMoved(false);
        }
    }

    @Override
    protected void processEntity(Entity pEntity, float deltaTime) {
        //perform action.
        Monster lMonster = (Monster)pEntity;

        if(lMonster.canPerformAction())
            lMonster.doSomething();
    }
//
//    @Override
//    public void addedToEngine(Engine engine) {
//        this.entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
//    }
}
