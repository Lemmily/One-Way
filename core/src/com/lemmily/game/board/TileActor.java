package com.lemmily.game.board;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lemmily.game.models.PlayerController;
import com.lemmily.game.utils.Assets;

/**
 * Created by emily on 18/05/15.
 */
public class TileActor extends Actor {
    private static TextureRegion theMultipleObjectTexture = Assets.get( "images/tiles.atlas", "empty");
    public Tile theTile;
    private TextureRegion theTexture;
    private TextureRegion theEntityTexture;
    private TextureRegion theObjectTexture;
    private Label theLabel;

    public TileActor() {
        theTile = new Tile(4, null); //empty tile

        theTexture = Assets.get("images/tiles.atlas", "bg");
        theEntityTexture = Assets.get("images/tiles.atlas", "empty");
        theObjectTexture = Assets.get("images/tiles.atlas", "empty");
        setSize(theTexture.getRegionWidth() * 2, theTexture.getRegionHeight() * 2);
    }

    public TileActor(final Tile pTile) {
        theTexture = Assets.get("images/tiles.atlas", "bg");
        //TODO: take these kind of direct links and give them to a manager/controller
        //TODO: keep the links in a map or something like that.
        theTile = pTile;
        pTile.theActor = this;

        if (theTile.isOccupied()) {
            setObjectTexture("images/tiles.atlas", theTile.getTexture());
        } else {
            theEntityTexture = Assets.get("images/tiles.atlas", "empty");
        }

        setSize(theTexture.getRegionWidth() * 2, theTexture.getRegionHeight() * 2);

        addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                System.out.println("enter " + pTile.thePos + " x:" + pTile.theActor.getX() + ", w:" + pTile.theActor.getWidth());
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

    public TextureRegion getEntityTexture() {
        if (theEntityTexture != null)
            return theEntityTexture;

        return Assets.get("images/tiles.atlas", "empty");
    }

    public TextureRegion getObjectTexture()
    {
        return theObjectTexture;
    }

    public static TextureRegion getMultipleObjectTexture()
    {
        return theMultipleObjectTexture;
    }

    public void setEntityTexture( TextureRegion pEntityTexture ) {
        theEntityTexture = pEntityTexture;
    }

    public void setObjectTexture(String pAtlas, String pRegion) {
        theEntityTexture = Assets.get(pAtlas, pRegion);
    }


    @Override
    public void draw( Batch pBatch, float parentAlpha )
    {
        pBatch.setColor( getColor() );
        pBatch.draw( getTexture(), getX(), getY(), getWidth(), getHeight() );
        if( theTile.isOccupied() )
        {
            pBatch.draw( getEntityTexture(), getX(), getY(), getWidth(), getHeight() );
        }
        if( theTile.hasItems() > 1)
        {
            //TODO: texture order? is this considered?
            pBatch.draw( getObjectTexture(), getX(), getY(), getWidth(), getHeight() );
        }
        else if (theTile.hasItems() > 0) {
            //TODO: multiple items image.
            pBatch.draw( getObjectTexture(), getX(), getY(), getWidth(), getHeight() );
        }
    }

    @Override
    public String toString() {
        String lString = "";

        lString += "x:" + this.getX() + "y:" + this.getY() + " (" + theTile + ")";

        return lString;
    }
}
