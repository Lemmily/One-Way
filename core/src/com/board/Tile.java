package com.board;

import com.models.entities.GameEntity;
import com.models.entities.Monster;

import java.util.Random;

/**
 * Created by emily on 18/05/15.
 */
public class Tile  {

    public int thePos;
    private GameEntity theOccupier;

    public Tile(int pPos) {
        thePos = pPos;
        Random lRandom = new Random();
        if (lRandom.nextInt(100) < 50)
            theOccupier = new Monster();
    }

    public GameEntity getOccupier() {
        return theOccupier;
    }

    public boolean isOccupied() {
        return theOccupier != null;
    }
}
