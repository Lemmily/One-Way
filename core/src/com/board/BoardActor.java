package com.board;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by emily on 18/05/15.
 *
 *
 * board visuals
 */
public class BoardActor extends Group {

    public BoardActor(Board pBoard) {
//        TextButton buttonPlay = new TextButton("HEllow", Assets.menuSkin);
//        addActor(buttonPlay);
        for (int i = 0; i < pBoard.theTiles.size(); i++) {
            TileActor lTile = new TileActor(pBoard.theTiles.get(i));
            lTile.setPosition(i * lTile.getWidth(), 0);
            this.addActor(lTile);
        }
//        this.setTouchable(Touchable.childrenOnly);
    }



}
