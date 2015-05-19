package com.models.entities;


import com.badlogic.ashley.signals.Signal;
import com.models.listeners.ActionListener;
import com.models.signals.ActionTaken;

/**
 * Created by emily on 19/05/15.
 */

public class Monster extends InteractableEntity implements ActionListener {

    public Monster() {
        super();
    }


    @Override
    public boolean canPerformAction() {
        if (theActionPoints > 10) return true;
        return false;
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
