package com.models.entities;

import com.badlogic.ashley.core.Entity;

/**
 * Created by emily on 19/05/15.
 */
public class InteractableEntity extends Entity {

    protected int theActionPoints;

    public InteractableEntity() {
        theActionPoints = 5;
    }


    /**
     * @return whether the entity can perform an action with current action points.
     */
    public boolean canPerformAction() {
        return false;
    }


    /**
     * temp to test system.
     */
    public void doSomething() {

    }


}
