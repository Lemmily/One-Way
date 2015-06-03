package com.models;

import com.badlogic.ashley.core.Engine;
import com.board.Board;
import com.board.BoardActor;
import com.models.entities.Player;

/**
 * Created by emily on 19/05/15.
 */
public class GameController {


    private static GameController INSTANCE = null;

    private Engine theEngine;
    private BoardActor theBoardActor;
    private Player thePlayer;

    public static GameController get() {
        return INSTANCE;
    }

    public static void init(Engine pEngine) {
        INSTANCE = new GameController(pEngine);
    }

    /**
     * singleton thing
     *
     * @param pEngine
     */
    public GameController(Engine pEngine) {
        theEngine = pEngine;
        theBoardActor = new BoardActor(pEngine, new Board(8));
        thePlayer = new Player();
    }

    public Engine getEngine() {
        return theEngine;
    }

    public BoardActor getBoardActor() {
        return theBoardActor;
    }

    public Player getPlayer() {
        return thePlayer;
    }
}
