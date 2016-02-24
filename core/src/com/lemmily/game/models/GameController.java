package com.lemmily.game.models;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.signals.Signal;
import com.lemmily.game.board.Board;
import com.lemmily.game.board.BoardActor;
import com.lemmily.game.models.entities.Player;
import com.lemmily.game.models.listeners.TileDroppedListener;
import com.lemmily.game.models.signals.TileDropped;

/**
 * Created by emily on 19/05/15.
 */
public class GameController implements TileDroppedListener {


    private static GameController INSTANCE = null;

    public static final Signal<TileDropped> theTileDroppedSignal = new Signal<>();
//    public static final Signal<ItemDropped> theItemDroppedSignal = new Signal<>();
    private Engine theEngine;
    private BoardActor theBoardActor;
    private Player thePlayer;

    /**
     * singleton thing
     *
     * @param pEngine
     */
    public GameController(Engine pEngine) {
        theEngine = pEngine;
        theBoardActor = new BoardActor(pEngine, new Board(8), new Player());
        thePlayer = new Player();
        theTileDroppedSignal.add(this);
    }

    public static GameController get() {
        return INSTANCE;
    }

    public static void init(Engine pEngine) {
        INSTANCE = new GameController(pEngine);
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


    @Override
    public void receive(Signal<TileDropped> signal, TileDropped object) {
        //create new tile.
        theBoardActor.addNewTile();
    }
}
