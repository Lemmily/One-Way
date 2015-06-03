package com.models.entities;

import com.badlogic.ashley.signals.Signal;
import com.models.components.EnemyComponent;
import com.models.components.StatsComponent;
import com.models.listeners.ActionListener;
import com.models.signals.ActionTaken;

import java.util.Random;

/**
 * Created by emily on 19/05/15.
 */

public class Monster extends GameEntity implements Actable, Interactable, ActionListener {

    private String theName;
    protected int theActionPoints;

    public Monster() {
        super("potion_health_small");
        Random lRandom = new Random();
        theActionPoints = lRandom.nextInt(10);
        add(new EnemyComponent());
        add(new StatsComponent(5,5,5,5,5,5,5));
    }
    public Monster(String pName) {
        super("potion_health_small");

        theName = pName;

        Random lRandom = new Random();
        theActionPoints = lRandom.nextInt(10);
        add(new EnemyComponent());
        add(new StatsComponent(5,5,5,5,5,5,5));
    }

    @Override
    public boolean canPerformAction() {
        return theActionPoints > 10;
    }

    @Override
    public void doSomething() {
        System.out.println( "Monster " + theName + " did an action!");
        theActionPoints -= 10;
    }

    @Override
    public void receive(Signal<ActionTaken> signal, ActionTaken pActionTaken) {
        theActionPoints += pActionTaken.theActionPoints;
    }



    public String toString() {
        String lString = "Monster ";

        lString += theName + ": ";
        lString += theActionPoints;

        return lString;
    }

}
