package com.lemmily.game.models.entities;

/**
 * Created by emily on 20/05/15.
 */
public interface Actable {


    /**
     * @return whether the entity can perform an action with current action points.
     */
    public boolean canPerformAction();


    /**
     * temp to test system.
     */
    public void doSomething();
}
