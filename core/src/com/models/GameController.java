package com.models;

import com.badlogic.ashley.signals.Signal;
import com.models.listeners.ActionListener;
import com.models.signals.ActionTaken;

/**
 * Created by emily on 19/05/15.
 */
public class GameController {


    private static GameController INSTANCE = null;
    private Signal<ActionTaken> thePlayerControlsSignal;

    public static GameController get() {
        return INSTANCE;
    }

    public static void init(GameController game) {
        INSTANCE = game;
    }



    public GameController() {
    }

    public void registerForActions(ActionListener pListener) {
        thePlayerControlsSignal.add(pListener);
    }
}
