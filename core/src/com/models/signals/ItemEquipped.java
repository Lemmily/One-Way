package com.models.signals;

import com.models.entities.Item;

/**
 * Created by emily on 22/07/15.
 */
public class ItemEquipped {

    private final Item theItem;
    private final boolean theIsEquipping;

    public ItemEquipped(Item pItem, boolean pEquipping) {
        theItem = pItem;
        theIsEquipping = pEquipping;
    }

    public boolean isEquipping() {
        return theIsEquipping;
    }

    public Item getItem() {
        return theItem;
    }
}
