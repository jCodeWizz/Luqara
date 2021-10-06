package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.craftingStation;
import dev.CodeWizz.engine.util.Textures;

public class CraftingStation extends ItemStack implements ITilePlacable {

	private Tile tile;
	
	public CraftingStation(int size) {
		super(size);
		
		this.maxSize = 2;
		this.size = 1;
		
		this.type = Type.CraftingStation;
		
		this.icon = Textures.get("craftingstation");
		this.idleTexture = Textures.get("air");
		this.runTexture = Textures.get("air");
		
		this.fromBlock = true;
		
		this.name = "Craftingstation";
		this.group = "Item";
		this.lore = "";
		
		this.tile = new craftingStation(0, 0, 0, 0, null);
		
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
		
		
		return true;
	}
}
