package com.board;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.models.GameController;
import com.models.PlayerController;
import com.models.entities.Monster;
import com.models.entities.Player;

import java.util.LinkedList;

/**
 * Created by emily on 18/05/15.
 * <p>
 * <p>
 * board visuals
 */
public class BoardActor extends Group {

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
            TileActor lTile = new TileActor(pBoard.theTiles.get(i));
            lTile.setPosition(lTile.getWidth() + 10 + i * (lTile.getWidth()), 0);
            theTiles.add(lTile);
            this.addActor(lTile);
        }
        registerListeners();
        addToEngine(pEngine);
//        this.setTouchable(Touchable.childrenOnly);

//        splashImage.addAction(Actions.sequence(Actions.alpha(0)
//                , Actions.fadeIn(0.75f), Actions.delay(1.5f), Actions.run(new Runnable() {
//            @Override
//            public void run() {
//                animationDone = true;
//            }
//        })));
    }

    private void addToEngine(Engine pEngine) {
        for (TileActor lTileActor : theTiles) {
            if (lTileActor.theTile.isOccupied())
                pEngine.addEntity(lTileActor.theTile.getOccupier());
        }
    }

    private void registerListeners() {
        for (TileActor lTileActor : theTiles) {
            if (lTileActor.theTile.isOccupied())
                PlayerController.get().registerListener((Monster) lTileActor.theTile.getOccupier());
        }
    }

    public void dropFirstTile() {
        dropTile(theTiles.peekFirst());
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
            pTile.addAction(Actions.sequence(Actions.parallel(Actions.moveTo(pTile.getX(), pTile.getY() - 128, 0.65f), Actions.fadeOut(0.65f)), Actions.run(new Runnable() {
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

            //move all subsequent tiles up
            TileActor lPrevious = pTile;
            for (int i = lIndex; i <= theTiles.size() - 1; i++) {
                TileActor lActor = theTiles.get(i);
                moveUp(lPrevious, lActor);
                lActor.theTile.thePos -= 1;
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
        }
        pTile.remove();
    }

    /**
     * moves the next tile to the previous tile's location.
     *
     * @param pPrevious
     * @param pNext
     */
    private void moveUp(TileActor pPrevious, final TileActor pNext) {
        pNext.addAction(Actions.sequence(
                Actions.moveTo(pPrevious.getX(), pPrevious.getY(), 1.0f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Moving the tile up" + pNext.theTile.thePos);
                        theAnimating = false;
                    }
                })));
    }

    /**
     * @return the board object
     */
    public Board board() {
        return theBoard;
    }

}
