package com.models.entities;

import com.badlogic.ashley.signals.Signal;
import com.models.PlayerController;
import com.models.listeners.ActionListener;
import com.models.signals.ActionTaken;
import com.utils.Enums;

/**
 * Created by emily on 06/08/15.
 */
public class StatMod implements ActionListener {


    /**
     * which stat is it modifying?
     */
    private Enums.Attributes theType;
    /**
     * turns ~~~ action points.
     */
    private int theTurnsActive;
    /**
     * how much is the stat modified?
     */
    private int theValue;

    public StatMod(Enums.Attributes pType, int pModifier, int pTurnsActive) {
        theType = pType;
        theValue = pModifier;
        theTurnsActive = pTurnsActive;
        PlayerController.get().registerListener(this);
    }

//    public


    @Override
    public void receive(Signal<ActionTaken> signal, ActionTaken pActionTaken) {
        if (pActionTaken != null) {
            theTurnsActive -= pActionTaken.theActionPoints;
        }

        if (theTurnsActive <= 0) {
            //diiiieeee modifier dieeee.
            PlayerController.get().deregisterListener(this);
        }
    }

    public int getValue() {
        return theValue;
    }

    public Enums.Attributes getType() {
        return theType;
    }

    public int getTurnsLeft() {
        return theTurnsActive;
    }
}
