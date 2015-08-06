package com.models.signals;

import com.models.entities.Item;

/**
 * Created by emily on 06/08/15.
 */
public class InventoryItemAdded {
    private Item theItem;

    public InventoryItemAdded(Item pItem) {
        theItem = pItem;
    }
}
