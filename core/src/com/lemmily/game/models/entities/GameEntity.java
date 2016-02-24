package com.lemmily.game.models.entities;

import com.badlogic.ashley.core.Entity;

public abstract class GameEntity extends Entity {


    public String theTexture;

    public GameEntity() {
        theTexture = "empty";
    }

    public GameEntity(String image) {
        theTexture = image;
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
