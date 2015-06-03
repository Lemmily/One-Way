package com.models.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.utils.Assets;

/**
 * Created by emily on 19/05/15.
 */
public abstract class GameEntity extends Entity {


    public TextureRegion theTexture;

    public GameEntity() {

    }

    public GameEntity(String image) {
        theTexture = Assets.get("icons/potions.atlas", image);
    }


    /**
     * called to get the information for the statistics window.
     *
     * @return
     */
    public String getStats() {
        return "OVERWRITE \"getStats()\" method";
    }
}
