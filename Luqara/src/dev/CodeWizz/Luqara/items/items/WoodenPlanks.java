package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.woodenPlanks;
import dev.CodeWizz.engine.util.Textures;

public class WoodenPlanks extends ItemStack implements ITilePlacable {

	private Tile tile;
	
	public WoodenPlanks(int size) {
		super(size);
		
		this.maxSize = 12;
		this.size = size;
		
		this.type = Type.WoodenPlanks;
		
		this.icon = Textures.get("woodenplanks");
		this.idleTexture = Textures.get("air");
		this.runTexture = Textures.get("air");
		
		this.fromBlock = true;
		
		this.name = "Wooden Planks";
		this.group = "Block";
		this.lore = "";
		
		this.tile = new woodenPlanks(0, 0, 0, 0, null);
		
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
		this.tile = new woodenPlanks(0, 0, 0, 0, null);
		
		return true;
	}
}
