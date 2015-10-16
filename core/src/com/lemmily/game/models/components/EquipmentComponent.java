package com.lemmily.game.models.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.lemmily.game.models.entities.EquipmentSlot;
import com.lemmily.game.models.entities.Item;
import com.lemmily.game.models.signals.InventoryItemAdded;
import com.lemmily.game.models.signals.ItemEquipped;

/**
 * Created by emily on 05/06/15.
 */
public class EquipmentComponent implements Component {
    private final Array<EquipmentSlot> theEquipmentSlotsTwo;
    private ArrayMap<String, Item> theEquipmentSlots;


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
    }


    public boolean equipItem(Item pItem) {
        if (hasSlot(pItem.equippableSlot())) {
            System.out.println("hi");
            //find slot and equip
        }
        return false;
    }

    private boolean hasSlot(String pSlot) {
        return theEquipmentSlots.containsKey(pSlot);
    }


}
