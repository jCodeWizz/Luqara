package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.crate;
import dev.CodeWizz.engine.util.Textures;

public class CrateItem extends ItemStack implements ITilePlacable {

	private Tile tile;
	
	public CrateItem(int size) {
		super(size);
		
		this.maxSize = 2;
		this.size = size;
		
		this.type = Type.Crate;
		
		this.icon = Textures.get("crate");
		this.idleTexture = Textures.get("air");
		this.runTexture = Textures.get("air");
		
		this.fromBlock = true;
		
		this.name = "Crate";
		this.group = "Item";
		this.lore = "";
		
		this.tile = new crate(0, 0, 0, 0, null);
		
		this.serialData = "004";
		
	}

	@Override
	public boolean place(Tile tile) {
		this.tile.setChunk(tile.getChunk());
		this.tile.setX(tile.getX());
		this.tile.setY(tile.getY());
		this.tile.setChunkX(tile.getChunkX());
		this.tile.setChunkY(tile.getChunkY());
		
		
		tile.getChunk().placeTile(this.tile);
		this.tile = new crate(0, 0, 0, 0, null);
		
		return true;
	}
}
