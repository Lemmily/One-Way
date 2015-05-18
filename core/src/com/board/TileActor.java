package com.board;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.utils.Assets;

/**
 * Created by emily on 18/05/15.
 */
public class TileActor extends Actor {


    private TextureRegion theTexture;

    public TileActor(final Tile pTile) {
        theTexture = Assets.get("images/tiles.atlas", "bg");

        setSize(theTexture.getRegionWidth(),
        addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//                super.enter(event, x, y, pointer, fromActor);
                System.out.println("enter " + pTile.thePos);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
//                super.exit(event, x, y, pointer, toActor);
                System.out.println("exit " + pTile.thePos);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("CLICKED " + pTile.thePos);
            }
        });
    }

    public TextureRegion getTexture() {
        return theTexture;
    }

    @Override
    public void draw(Batch pBatch, float parentAlpha) {
        pBatch.setColor(getColor());
        pBatch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());
    }

}
