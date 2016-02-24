package com.lemmily.game.models.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.lemmily.game.models.entities.EquipmentSlot;
import com.lemmily.game.models.entities.Item;
import com.lemmily.game.models.signals.InventoryItemAdded;
import com.lemmily.game.models.signals.ItemEquipped;


public class EquipmentSlotComponent implements Component {
    private final Array<EquipmentSlot> theEquipmentSlotsTwo;
    private ArrayMap<String, EquipmentSlot> theEquipmentSlots;
    private ArrayMap<Item, Integer> theItems;


    /**
     * TODO: not really sure how to deal with equipping items....
     * for things like attack and defense calculations.
     */
    public EquipmentSlotComponent( Array<String> pEquipSlots, Signal<ItemEquipped> pEquipSignal, Signal<InventoryItemAdded> pInventorySignal) {
        theEquipmentSlots = new ArrayMap<>();
        theEquipmentSlotsTwo = new Array<>();
        for (int i = 0; i < pEquipSlots.size; i++) {
            theEquipmentSlots.put(pEquipSlots.get(i), null);
            theEquipmentSlotsTwo.add(new EquipmentSlot(pEquipSlots.get(i), pEquipSignal, pInventorySignal));
        }

        theItems = new ArrayMap<>(  );
    }


    public boolean equipItem(Item pItem) {
        if (hasSlot(pItem.equippableSlot())) {
            System.out.println("hi " + pItem + " can be equipped.");
            //find slot and equip
            return true;
        }
        return false;
    }

    public Item unequipItem( Item pItem )
    {
        //TODO: cursed items can't be unequipped.

        if(! hasSlot( pItem.equippableSlot() ))
            return pItem;
        for( EquipmentSlot eSlot : theEquipmentSlotsTwo ) {
            if( eSlot.hasItemEquipped( pItem ) ) {
                if (theItems.containsKey( pItem ))
                    theItems.put( pItem, theItems.get(pItem) + 1 );
                else {
                    theItems.put( pItem, 1 );
                }
                return pItem;
            }
        }
        return null; //not successful.
    }

	public boolean hasSlot(String pSlot) {
        return theEquipmentSlots.containsKey(pSlot);
    }

	public Array<EquipmentSlot> getSlots() {
        return theEquipmentSlotsTwo;
    }

	public Array<Item> getItems() {
        Array<Item> lItems = new Array<>(  );
        for( int i = 0; i < theItems.keys().toArray().size; i++ )
        {
            Item lItem = theItems.keys().toArray().get( i );
            for( int j = 0; j < theItems.get( lItem ); j++ )
            {
                lItems.add( lItem );
            }
        }
        return lItems;
    }

}
