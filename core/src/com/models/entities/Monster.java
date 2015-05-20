package com.models.entities;


import com.badlogic.ashley.signals.Signal;
import com.models.components.EnemyComponent;
import com.models.components.StatsComponent;
import com.models.listeners.ActionListener;
import com.models.signals.ActionTaken;

/**
 * Created by emily on 19/05/15.
 */

public class Monster extends GameEntity implements Actable, Interactable, ActionListener {

    protected int theActionPoints;

    public Monster() {
        super("potion_health_small");
        theActionPoints = 5;
        add(new EnemyComponent());
        add(new StatsComponent(5,5,5,5,5,5,5));
    }


    @Override
    public boolean canPerformAction() {
        return theActionPoints > 10;
    }

    @Override
    public void doSomething() {
        System.out.println("did an action!");
        theActionPoints -= 10;
    }

    @Override
    public void receive(Signal<ActionTaken> signal, ActionTaken pActionTaken) {
        theActionPoints += pActionTaken.theActionPoints;
    }
}
