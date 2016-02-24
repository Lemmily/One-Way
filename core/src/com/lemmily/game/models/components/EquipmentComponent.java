package com.lemmily.game.models.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.lemmily.game.models.entities.EquipmentSlot;
import com.lemmily.game.models.entities.Item;
import com.lemmily.game.models.signals.InventoryItemAdded;
import com.lemmily.game.models.signals.ItemEquipped;

public class EquipmentComponent implements Component {
    private final Array<EquipmentSlot> theEquipmentSlotsTwo;
    private ArrayMap<String, Item> theEquipmentSlots;
    private ArrayMap<String, Item> theItems;


    /**
     * TODO: not really sure how to deal with equipping items....
     * for things like attack and defense calculations.
     */
    public EquipmentComponent(Array<String> pEquipSlots, Signal<ItemEquipped> pEquipSignal, Signal<InventoryItemAdded> pInventorySignal) {
        theEquipmentSlots = new ArrayMap<String, Item>();
        theEquipmentSlotsTwo = new Array<EquipmentSlot>();
        for (int i = 0; i < pEquipSlots.size; i++) {
            theEquipmentSlots.put(pEquipSlots.get(i), null);
            theEquipmentSlotsTwo.add(new EquipmentSlot(pEquipSlots.get(i), pEquipSignal, pInventorySignal));
        }

        theItems = new ArrayMap<>(  );
    }


    public boolean equipItem(Item pItem) {
        if (hasSlot(pItem.equippableSlot())) {
            System.out.println("hi");
            //find slot and equip
            return true;
        }
        return false;
    }

    public boolean unequipItem( Item pItem )
    {
        return false; //not successful.
    }

    private boolean hasSlot(String pSlot) {
        return theEquipmentSlots.containsKey(pSlot);
    }



}
