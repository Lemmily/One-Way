package com.board;

import com.models.entities.GameEntity;
import com.models.entities.Monster;

import java.util.Random;

/**
 * Created by emily on 18/05/15.
 */
public class Tile  {
    public int thePos; // -1 is player pos, 0 < are board slots
    private GameEntity theOccupier;
    public TileActor theActor;

    public Tile(int pPos) {
        thePos = pPos;
        Random lRandom = new Random();
        if (lRandom.nextInt(100) < 50) {
            theOccupier = new Monster("" + pPos);
            System.out.println(theOccupier);

            ///// moved so that the gamecontroller can make the board actor.
//            PlayerController.get().registerListener((Monster) theOccupier);
//            GameController.get().getEngine().addEntity(theOccupier);
        }
    }

    public Tile(int pPos, GameEntity pGameEntity) {
        thePos = pPos;
        theOccupier = pGameEntity;
    }


    public GameEntity getOccupier() {
        return theOccupier;
    }

    public boolean isOccupied() {
        return theOccupier != null;
    }

    public String getTexture() {
        return theOccupier.theTexture;
    }

}
