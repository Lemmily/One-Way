package com.lemmily.game.models.entities;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.utils.Array;
import com.lemmily.game.models.components.Components;
import com.lemmily.game.models.components.EquipmentComponent;
import com.lemmily.game.models.components.PlayerComponent;
import com.lemmily.game.models.components.StatsComponent;
import com.lemmily.game.models.listeners.EquipmentListener;
import com.lemmily.game.models.signals.InventoryItemAdded;
import com.lemmily.game.models.signals.ItemEquipped;

/**
 * Created by emily on 20/05/15.
 */
public class Player extends GameEntity implements EquipmentListener {


    public Player() {
        super("potion_health_large");
        add(new StatsComponent(1, 1, 1, 1, 1, 1, 1));
        add(new PlayerComponent());
        Array<String> lSlots = new Array<>();
        lSlots.add("Torso");
        lSlots.add("Legs");
        lSlots.add("Gloves");
        lSlots.add("Head");
        lSlots.add("Left Hand");
        lSlots.add("Right Hand");
        lSlots.add("Back");
        lSlots.add("Ring Right");
        lSlots.add("Ring Left");

        Signal<ItemEquipped> lEquippedSignal = new Signal<>();
        Signal<InventoryItemAdded> lInventorySignal = new Signal<>();
        add(new EquipmentComponent(lSlots, lEquippedSignal, lInventorySignal));
        lEquippedSignal.add(this);
    }


    @Override
    public String getStats() {
        return "Player!";
    }


    @Override
    public void unequipItem(Item pItem) {

    }

    @Override
    public void equipItem(Item pItem) {
        boolean lsuccess = Components.EQUIPMENT.get(this).equipItem(pItem);
        if (lsuccess) {

        }
    }

    @Override
    public void receive(Signal<ItemEquipped> signal, ItemEquipped pItemEquipped) {
        if (pItemEquipped.isEquipping()) {
            //update the stats.
        } else {
            //update the stats.
        }
    }
}
