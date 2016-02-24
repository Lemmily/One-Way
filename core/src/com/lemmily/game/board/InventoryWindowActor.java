package com.lemmily.game.board;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.lemmily.game.models.GameController;
import com.lemmily.game.models.components.EquipmentComponent;
import com.lemmily.game.utils.Assets;


public class InventoryWindowActor extends Window
{
	private Label theLabel;
	public InventoryWindowActor(String title) {
		super(title, Assets.menuSkin);
//		this.setTouchable( Touchable.disabled);
		theLabel = new Label( "THIS IS THE INVENTORY", Assets.menuSkin );
		add(theLabel);
	}


	public void init() {
		lSlots = GameController.get().getPlayer().getComponent( EquipmentComponent.class).getSlots();

	}
}
