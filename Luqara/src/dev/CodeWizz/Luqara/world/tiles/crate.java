package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.items.inventories.CrateInventory;
import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;

public class crate extends Tile implements ITileEntity {

	private CrateInventory inventory;
	
	public crate(int x, int y, int cx, int cy, Chunk chunk) {
		super(x, y, cx, cy, chunk);
		
		this.id = TileID.Background;
		
		
		inventory = new CrateInventory(8);
		
	}

	@Override
	public void click(GameContainer gc) {
		inventory.open(gc);
	}

	@Override
	public Image getTexture() {
		return Textures.get("crate");
	}

	public CrateInventory getInventory() {
		return inventory;
	}

	public void setInventory(CrateInventory inventory) {
		this.inventory = inventory;
	}
}
