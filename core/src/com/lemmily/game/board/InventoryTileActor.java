package com.lemmily.game.board;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.lemmily.game.models.entities.Item;
import com.lemmily.game.utils.Assets;

/**
 * Created by emily on 05/05/16.
 */
public class InventoryTileActor extends Actor
{

	private final TextureRegion theTexture;
	private Item theItem;

	public InventoryTileActor( Item pItem) {
		theItem = pItem;
		theTexture = Assets.get("images/items_x24.atlas", pItem.theTexture);

		setSize( theTexture.getRegionWidth() * 2, theTexture.getRegionHeight() * 2 );
	}
}
