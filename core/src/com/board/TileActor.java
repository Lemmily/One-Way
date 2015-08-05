package com.board;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.models.PlayerController;
import com.utils.Assets;

/**
 * Created by emily on 18/05/15.
 */
public class TileActor extends Actor {
    private TextureRegion theTexture;
    private TextureRegion theObjectTexture;

    private Label theLabel;

    public Tile theTile;

    public TileActor() {
        theTile = new Tile(4, null); //empty tile

        theTexture = Assets.get("images/tiles.atlas", "bg");
        theObjectTexture = Assets.get("images/tiles.atlas", "empty");

        setSize(theTexture.getRegionWidth() * 2, theTexture.getRegionHeight() * 2);
    }

    public TileActor(final Tile pTile) {
        theTexture = Assets.get("images/tiles.atlas", "bg");
        theTile = pTile;
        pTile.theActor = this;

        if (theTile.isOccupied()) {
            setObjectTexture("icons/potions.atlas", theTile.getTexture());
        } else {
            theObjectTexture = Assets.get("images/tiles.atlas", "empty");
        }

        setSize(theTexture.getRegionWidth() * 2, theTexture.getRegionHeight() * 2);

        addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                System.out.println("enter " + pTile.thePos);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                System.out.println("exit " + pTile.thePos);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("CLICKED " + pTile.thePos);
                //broadcast action performed.
                if (pTile.isOccupied())
                    PlayerController.get().actionPerformed(pTile.getOccupier());
                else {
                    PlayerController.get().actionPerformed();
                }
            }
        });
    }

    public TextureRegion getTexture() {
        return theTexture;
    }

    public TextureRegion getObjectTexture() {
        return theObjectTexture;
    }

    public void setObjectTexture(TextureRegion pObjectTexture) {
        theObjectTexture = pObjectTexture;
    }

    public void setObjectTexture(String pAtlas, String pRegion) {
        theObjectTexture = Assets.get(pAtlas, pRegion);
    }

    @Override
    public void draw(Batch pBatch, float parentAlpha) {
        pBatch.setColor(getColor());
        pBatch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());
        if (theTile.isOccupied()) {
            pBatch.draw(getObjectTexture(), getX(), getY(), getWidth(), getHeight());
        }
    }

}
