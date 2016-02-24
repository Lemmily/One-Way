package com.lemmily.game.board;


import com.lemmily.game.LibGdxUtils;
import com.lemmily.game.models.entities.Monster;

import java.util.LinkedList;
import java.util.Stack;

import static com.lemmily.game.LibGdxUtils.logger;

/**
 * Created by emily on 18/05/15.
 * <p>
 * <p>
 * board logic
 */
public class Board {
    public LinkedList<Tile> theTiles = new LinkedList<>();
    private int theSize;

    public Board(int pSize) {
        theSize = pSize;

        for (int i = 0; i < pSize; i++) {
            theTiles.add(new Tile(i));
        }
    }

    public void addTile(Tile pTile) {
        theTiles.add(pTile);
//        pTile.thePos = theTiles.indexOf(pTile);
    }

    public Tile findTileWithMonster(Monster pMonster) {
        for (Tile lTile : theTiles) {
            if (lTile.isOccupied() && pMonster == lTile.getOccupier())
                logger.debug( "Found the monster " + pMonster + " in the tile: " + lTile );
                return lTile;
        }
        logger.debug( "Could not find the monster in any tiles" );
        return null;
    }

    public void disposeTile(Tile pTile) {
        logger.debug( "Removing the tile " + pTile );
        theTiles.remove(pTile);
    }
}
