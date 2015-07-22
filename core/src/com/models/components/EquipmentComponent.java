package com.models.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.models.entities.EquipmentSlot;
import com.models.entities.Item;

/**
 * Created by emily on 05/06/15.
 */
public class EquipmentComponent extends Component {
    private final Array<EquipmentSlot> theEquipmentSlotsTwo;
    private ArrayMap<String, Item> theEquipmentSlots;

    /**
     * TODO: not really sure how to deal with equipping items....
     * for things like attack and defense calculations.
     */
    public EquipmentComponent(Array<String> pEquipSlots) {
        theEquipmentSlots = new ArrayMap<String, Item>();
        theEquipmentSlotsTwo = new Array<EquipmentSlot>();
        for (int i = 0; i < pEquipSlots.size; i++) {
            theEquipmentSlots.put(pEquipSlots.get(i), null);
            theEquipmentSlotsTwo.add(new EquipmentSlot(pEquipSlots.get(i)));
        }
    }


    public void equipItem(Item pItem) {
        if (hasSlot(pItem.equippableSlot())) {
            System.out.println("hi");
        }
    }

    private boolean hasSlot(String pSlot) {
        if (theEquipmentSlots.containsKey(pSlot))
            return true;
        return false;
    }


}
