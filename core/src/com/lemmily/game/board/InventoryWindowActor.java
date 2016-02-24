package com.lemmily.game.board;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.lemmily.game.models.GameController;
import com.lemmily.game.models.components.EquipmentSlotComponent;
import com.lemmily.game.models.entities.EquipmentSlot;
import com.lemmily.game.models.entities.Item;
import com.lemmily.game.models.listeners.InventoryListener;
import com.lemmily.game.models.signals.InventoryItemAdded;
import com.lemmily.game.utils.Assets;


public class InventoryWindowActor extends Window implements InventoryListener
{
	private Label theLabel;

	public InventoryWindowActor(String title) {
		super(title, Assets.menuSkin);
//		this.setTouchable( Touchable.disabled);
		theLabel = new Label( "THIS IS THE INVENTORY", Assets.menuSkin );
		add(theLabel);
	}


	public void init() {
		EquipmentSlotComponent lEquipComp = GameController.get().getPlayer().getComponent( EquipmentSlotComponent.class);
		Array< EquipmentSlot > lSlots = lEquipComp.getSlots();
		Array< Item > lItems = lEquipComp.getItems();

		for( EquipmentSlot eSlot: lSlots)
		{
			add(new InventoryTileActor( eSlot.theItem ));
		}

		add(new Label("Items", Assets.menuSkin));
		for( Item lItem: lItems)
		{
			add( new InventoryTileActor( lItem ));
		}

	}

	@Override
	public void receive( Signal< InventoryItemAdded > signal, InventoryItemAdded object )
	{
		//refresh the slots?
	}
}
