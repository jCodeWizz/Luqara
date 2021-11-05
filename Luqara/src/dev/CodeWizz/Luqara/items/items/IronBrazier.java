package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.ironBrazier;
import dev.CodeWizz.engine.util.Textures;

public class IronBrazier extends ItemStack implements ITilePlacable {

	private Tile tile;
	
	public IronBrazier(int size) {
		super(size);
		
		this.maxSize = 2;
		this.size = size;
		
		this.type = Type.IronBrazier;
		
		this.icon = Textures.get("ironbrazier");
		this.idleTexture = Textures.get("air");
		this.runTexture = Textures.get("air");
		
		this.fromBlock = true;
		
		this.name = "IronBrazier";
		this.group = "Tile";
		this.lore = "";
		
		this.tile = new ironBrazier(0, 0, 0, 0, null);
		
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
