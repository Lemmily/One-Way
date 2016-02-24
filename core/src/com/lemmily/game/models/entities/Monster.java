package com.lemmily.game.models.entities;

import com.badlogic.ashley.signals.Signal;
import com.lemmily.game.models.components.EnemyComponent;
import com.lemmily.game.models.components.StatsComponent;
import com.lemmily.game.models.listeners.ActionListener;
import com.lemmily.game.models.signals.ActionTaken;

import java.util.Random;

/**
 * Created by emily on 19/05/15.
 */

public class Monster extends GameEntity implements Actable, Interactable, ActionListener {

    protected int theActionPoints;
    private String theName;

    public Monster() {
        super("goblin");
        Random lRandom = new Random();
        theActionPoints = lRandom.nextInt(10);
        add(new EnemyComponent());
        add(new StatsComponent(5, 5, 5, 5, 5, 5, 5));
    }

    public Monster(String pName) {
        super("goblin");

        theName = pName;

        Random lRandom = new Random();
        theActionPoints = lRandom.nextInt(10);
        add(new EnemyComponent());
        add(new StatsComponent(5, 5, 5, 5, 5, 5, 5));
    }

    @Override
    public boolean canPerformAction() {
        return theActionPoints > 10;
    }

    @Override
    public void doSomething() {
        System.out.println("Monster " + theName + " did an action!");
        theActionPoints -= 10;
    }

    @Override
    public void receive(Signal<ActionTaken> signal, ActionTaken pActionTaken) {
        if (pActionTaken != null) {
            theActionPoints += pActionTaken.theActionPoints;
        }
    }


    public String toString() {
        String lString = "Monster ";

        lString += theName + ": ";
        lString += theActionPoints;

        return lString;
    }

    @Override
    public String getStats() {

        return "Monsters are regularly evil creatures!.. ";
    }

}
