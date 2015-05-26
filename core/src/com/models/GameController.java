package com.models;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.signals.Signal;
import com.models.signals.ActionTaken;

/**
 * Created by emily on 19/05/15.
 */
public class GameController {


    private static GameController INSTANCE = null;


    private Signal<ActionTaken> thePlayerControlsSignal;
    private Engine theEngine;

    public static GameController get() {
        return INSTANCE;
    }

    public static void init(Engine pEngine) {
        INSTANCE = new GameController(pEngine);
    }

    public GameController(Engine pEngine) {
        theEngine = pEngine;
    }

    public Engine getEngine() {
        return theEngine;
    }
}
