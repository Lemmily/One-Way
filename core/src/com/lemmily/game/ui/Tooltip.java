package com.lemmily.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.lemmily.game.utils.Assets;

/**
 * Created by emily on 20/05/15.
 */


public class Tooltip extends Label {
    public Tooltip(int pX, int pY) {
        super("", Assets.menuSkin);
        setSize(300, 30);
        setPosition(pX, pY, Align.bottomLeft);
    }
}
