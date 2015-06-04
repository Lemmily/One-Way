package com.models.entities;

import com.models.components.PlayerComponent;
import com.models.components.StatsComponent;

/**
 * Created by emily on 20/05/15.
 */
public class Player extends GameEntity {

    public Player() {
        super("potion_health_large");
        add(new StatsComponent(1, 1, 1, 1, 1, 1, 1));
        add(new PlayerComponent());
    }


    @Override
    public String getStats() {
        return "Player!";
    }
}
