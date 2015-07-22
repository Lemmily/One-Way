package com.models.entities;

/**
 * Created by emily on 29/05/15.
 */
public class Item extends GameEntity {
    private String theEquipableSlot;

    public Item() {

    }

    /**
     * @return the slot that this item can be equipped in.
     */

    public String equippableSlot() {

        return theEquipableSlot;
    }
}
