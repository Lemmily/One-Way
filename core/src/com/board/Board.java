package com.board;

import java.util.Stack;

/**
 * Created by emily on 18/05/15.
 *
 *
 * board logic
 */
public class Board {

    private int theSize;

    public Stack<Tile> theTiles = new Stack<>();


    public Board(int pSize) {
        theSize = pSize;

        for (int i = 0; i < pSize; i++) {
            theTiles.push(new Tile(i));
        }
    }


    public void addTile(Tile pTile) {
        theTiles.push(pTile);

    }
}
