package com.board;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.models.PlayerController;
import com.models.entities.Monster;

import java.util.LinkedList;

/**
 * Created by emily on 18/05/15.
 *
 *
 * board visuals
 */
public class BoardActor extends Group {

    private Board theBoard;

    private LinkedList<TileActor> theTiles;

    public BoardActor(Engine pEngine, Board pBoard) {
        theTiles = new LinkedList<>();
        theBoard = pBoard;
//        TextButton buttonPlay = new TextButton("HEllow", Assets.menuSkin);
//        addActor(buttonPlay);
        for (int i = 0; i < pBoard.theTiles.size(); i++) {
            TileActor lTile = new TileActor(pBoard.theTiles.get(i));
            lTile.setPosition(i * (lTile.getWidth()), 0);
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
    public void dropTile(final TileActor pTile) {
        //move tile out
        pTile.addAction(Actions.sequence(Actions.moveTo(pTile.getX(), pTile.getY() - 64, 0.75f), Actions.fadeOut(0.75f), Actions.run(new Runnable() {
            @Override
            public void run() {
                pTile.remove();

            }
        })));


        int lIndex = theTiles.indexOf(pTile);
        theTiles.remove(pTile);

        //move all subsequent tiles up
        TileActor lPrevious = pTile;
        for (int i = lIndex; i <= theTiles.size() - 1; i++) {
            TileActor lActor = theTiles.get(i);
            moveUp(lPrevious, lActor);
            lActor.theTile.thePos -= 1;
            lPrevious = lActor;
        }

    }

    private void moveUp(TileActor pPrevious, final TileActor pNext) {
        pNext.addAction(Actions.sequence(
                Actions.moveTo(pPrevious.getX(), pPrevious.getY(), 1.0f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Moving the tile up" + pNext.theTile.thePos);
                    }
                })));
    }

    public Board board() {
        return theBoard;
    }

}
