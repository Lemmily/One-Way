package com.lemmily.game.board;


import com.lemmily.game.models.entities.Monster;

import java.util.LinkedList;
import java.util.Stack;

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
        theTiles.push(pTile);
        pTile.thePos = theTiles.indexOf(pTile);
    }

    public Tile findTileWithMonster(Monster pMonster) {
        for (Tile lTile : theTiles) {
            if (lTile.isOccupied() && pMonster == lTile.getOccupier())
                return lTile;
        }
        return null;
    }

    public void disposeTile(Tile pTile) {
        theTiles.remove(pTile);
    }
}
