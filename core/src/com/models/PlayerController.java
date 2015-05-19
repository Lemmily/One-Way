package com.models;

import com.badlogic.ashley.signals.Signal;
import com.models.listeners.ActionListener;
import com.models.signals.ActionTaken;

/**
 * Created by emily on 19/05/15.
 */
public class PlayerController {
    private static PlayerController INSTANCE;

    public static PlayerController get() {
        return INSTANCE;
    }
    public static void init() {
        INSTANCE = new PlayerController();
    }


    private final Signal<ActionTaken> thePlayerControlsSignal;
    private boolean thePlayerMoved;

    /**
     * Singleton(??) To control the interactions made by the player
     */
    private PlayerController() {
        thePlayerControlsSignal = new Signal<>();
    }

    public void actionPerformed(ActionTaken pAction) {
        thePlayerControlsSignal.dispatch(pAction);
        thePlayerMoved = true;
    }

    public void registerListener(ActionListener pListener) {
        thePlayerControlsSignal.add(pListener);
    }

    public boolean playerMoved() {
        return thePlayerMoved;
    }

    public void setPlayerMoved(boolean pPlayerMoved) {
        thePlayerMoved = pPlayerMoved;
    }
}
