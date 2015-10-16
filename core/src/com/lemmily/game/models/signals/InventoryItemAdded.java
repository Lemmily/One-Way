package com.lemmily.game.models.signals;


import com.lemmily.game.models.entities.Item;

/**
 * Created by emily on 06/08/15.
 */
public class InventoryItemAdded {
    private Item theItem;

    public InventoryItemAdded(Item pItem) {
        theItem = pItem;
    }
}
