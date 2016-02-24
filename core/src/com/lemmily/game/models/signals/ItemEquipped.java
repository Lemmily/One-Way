package com.lemmily.game.models.signals;

import com.lemmily.game.models.entities.Item;

/**
 * signal that is fired when
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
