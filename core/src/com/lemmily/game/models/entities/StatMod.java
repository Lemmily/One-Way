package com.lemmily.game.models.entities;

import com.badlogic.ashley.signals.Signal;
import com.lemmily.game.models.PlayerController;
import com.lemmily.game.models.listeners.ActionListener;
import com.lemmily.game.models.signals.ActionTaken;
import com.lemmily.game.utils.Enums;

/**
 * Created by emily on 06/08/15.
 */
public class StatMod implements ActionListener {


    /**
     * which stat is it modifying?
     */
    private Enums.Attributes theType;
    /**
     * turns == action points. How many turns should the modifier be active? -1 permenant.
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
