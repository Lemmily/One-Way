package com.lemmily.game.models.listeners;

import com.badlogic.ashley.signals.Listener;
import com.lemmily.game.models.entities.Item;
import com.lemmily.game.models.signals.ItemEquipped;

/**
 * Created by emily on 22/07/15.
 */
public interface EquipmentListener extends Listener<ItemEquipped> {

    void unequipItem(Item pItem);

    void equipItem(Item pItem);
}
