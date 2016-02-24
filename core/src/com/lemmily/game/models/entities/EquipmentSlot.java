package com.lemmily.game.models.entities;

import com.badlogic.ashley.signals.Signal;
import com.lemmily.game.models.signals.InventoryItemAdded;
import com.lemmily.game.models.signals.ItemEquipped;

/**
 * Created by emily on 22/07/15.
 */
public class EquipmentSlot {


    private String theSlot;
    private Signal<ItemEquipped> theEquippedSignal;
    private Signal<InventoryItemAdded> theInventoryItemAddedSignal;
    public Item theItem;

    public EquipmentSlot(String pSlot, Signal<ItemEquipped> pEquippedSignal, Signal<InventoryItemAdded> pInventoryItemAddedSignal) {

        theSlot = pSlot;

        theEquippedSignal = pEquippedSignal;
        theInventoryItemAddedSignal = pInventoryItemAddedSignal;
    }


    /**
     * @param pItem just completely overrides anything currently int he slot.
     */
    public void equipItem(Item pItem) {
        if (theItem != null) {
            theInventoryItemAddedSignal.dispatch(new InventoryItemAdded(theItem));
        }
        theItem = pItem;
        theEquippedSignal.dispatch(new ItemEquipped(pItem, true));
    }

    /**
     * @param pItem new item to equip.
     * @return item previously in equipment slot.
     */
    public Item replaceItem(Item pItem) {

        return null;
    }

    /**
     * @return true if the slot has something equipped.
     * @param pItem Item that we want to know if it's equipped.
     */
    public boolean hasItemEquipped( Item pItem )
    {
        return theItem != null && theItem == pItem;
    }

    /**
     * @return true if the slot has something equipped.
     */
    public boolean hasItemEquipped( ) {
        return hasItemEquipped( null );
    }


    /**
     * @return previously equipped item.
     */
    public Item unequip() {
        Item lItem = theItem;
        theItem = null;
        return lItem;
    }
}
