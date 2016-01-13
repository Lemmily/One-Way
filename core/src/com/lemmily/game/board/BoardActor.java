package com.lemmily.game.board;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.lemmily.game.models.GameController;
import com.lemmily.game.models.PlayerController;
import com.lemmily.game.models.entities.Monster;
import com.lemmily.game.models.entities.Player;
import com.lemmily.game.models.signals.TileDropped;

import java.util.LinkedList;

/**
 * Created by emily on 18/05/15.
 * <p>
 * <p>
 * board visuals
 */
public class BoardActor extends Group {

    private static final float TILE_WIDTH = 64.0f;
    private Board theBoard;

    private LinkedList<TileActor> theTiles;
    private TileActor thePlayer;
    private boolean theAnimating = false;

    public BoardActor(Engine pEngine, Board pBoard, Player pPlayer) {
        thePlayer = new TileActor(new Tile(-1, pPlayer));
        thePlayer.setPosition(0, 0);
        addActor(thePlayer);
        theTiles = new LinkedList<>();
        theBoard = pBoard;
//        TextButton buttonPlay = new TextButton("HEllow", Assets.menuSkin);
//        addActor(buttonPlay);
        for (int i = 0; i < pBoard.theTiles.size(); i++) {
            TileActor lTileActor = new TileActor(pBoard.theTiles.get(i));
            lTileActor.setPosition(lTileActor.getWidth() + 10 + i * (lTileActor.getWidth()), 0);
            theTiles.add(lTileActor);
            addActor(lTileActor);
        }
        registerListeners();
        addAllToEngine(pEngine);
//        this.setTouchable(Touchable.childrenOnly);

//        splashImage.addAction(Actions.sequence(Actions.alpha(0)
//                , Actions.fadeIn(0.75f), Actions.delay(1.5f), Actions.run(new Runnable() {
//            @Override
//            public void run() {
//                animationDone = true;
//            }
//        })));
    }

    private void addAllToEngine(Engine pEngine) {
        for (TileActor lTileActor : theTiles) {
            if (lTileActor.theTile.isOccupied())
                pEngine.addEntity(lTileActor.theTile.getOccupier());
        }
    }

    private void addToEngine(TileActor pTileActor) {
        if (pTileActor.theTile.isOccupied()) {
            GameController.get().getEngine().addEntity(pTileActor.theTile.getOccupier());
        }
    }

    private void registerListeners() {
        for (TileActor lTileActor : theTiles) {
            if (lTileActor.theTile.isOccupied())
                PlayerController.get().registerListener((Monster) lTileActor.theTile.getOccupier());
        }
    }

    public void dropFirstTile() {
        if (!theAnimating) {
            dropTile(theTiles.peekFirst());
        }
    }

    /**
     * makes the tile visibly drop whilst signalling all tiles to move up a space. once animation completed
     * pTile is removed from systems and engines
     *
     * @param pTile
     */
    public void dropTile(final TileActor pTile) {

        if (!theAnimating) {
            //move tile out
            pTile.addAction(Actions.sequence(Actions.parallel(Actions.moveTo(pTile.getX(), pTile.getY() - 128, 0.80f), Actions.fadeOut(0.85f)), Actions.run(new Runnable() {
                @Override
                public void run() {
                    removeTile(pTile);
                }
            })));

//            ));

//            pTile.addAction(Actions.run(new Runnable() {
//                @Override
//                public void run() {
//                    removeTile(pTile);
//                }
//            }));

            int lIndex = theTiles.indexOf(pTile);
            theTiles.remove(pTile);
            theBoard.disposeTile(pTile.theTile);

            //move all subsequent tiles up
            TileActor lPrevious = pTile;
            for (int i = lIndex; i <= theTiles.size() - 1; i++) {
                theAnimating = true;
                TileActor lActor = theTiles.get(i);
                lActor.theTile.thePos -= 1;
                moveUp(lActor);
                lPrevious = lActor;
                theAnimating = true;
            }
        }
    }

    /**
     * removes and deregisters the occupier from  listening to the signals and removes from the engine.
     *
     * @param pTile
     */
    private void removeTile(TileActor pTile) {
        if (pTile.theTile.isOccupied()) {
            PlayerController.get().deregisterListener((Monster) pTile.theTile.getOccupier());
            GameController.get().getEngine().removeEntity(pTile.theTile.getOccupier());
            theBoard.disposeTile(pTile.theTile);
        }
        pTile.remove();
        GameController.theTileDroppedSignal.dispatch(new TileDropped());
    }

    /**
     * moves the next tile to the previous tile's location.
     *
//     * @param pPrevious
     * @param pNext
     */
    private void moveUp(final TileActor pNext) {
        theAnimating = true;
        pNext.addAction(Actions.sequence(
                Actions.moveTo(TILE_WIDTH + 10 + theTiles.indexOf(pNext) * TILE_WIDTH, pNext.getY(), 1.0f),
                Actions.run(() -> {
                    System.out.println("Moving the tile up" + pNext.theTile.thePos);
                    theAnimating = false;
                })));
    }

//    private void moveToEnd( TileActor pNext) {
//        theAnimating = true;
//        pNext.addAction(Actions.sequence(
//                //TODO: change hardcoded nessssss for the last position!
//                Actions.moveTo(522.0f, 0, 1.0f),
//                Actions.run(() -> {
//                    System.out.println("Moving the tile up" + pNext.theTile.thePos);
//                    theAnimating = false;
//                })));
//    }


    /**
     * @return the board object
     */
    public Board board() {
        return theBoard;
    }

    public void addNewTile() {
        TileActor lTileActor = new TileActor(new Tile(theTiles.size()));//theTiles.size()));
        addActor(lTileActor);
        lTileActor.setPosition(Gdx.graphics.getWidth(), 0);
        System.out.println(lTileActor);
        addToEngine(lTileActor);
        theTiles.add(lTileActor);
        theBoard.addTile(lTileActor.theTile);
        moveUp(lTileActor);
    }

}
