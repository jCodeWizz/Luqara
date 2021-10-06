package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;

public class craftingStation extends Tile implements ITileEntity {

	public craftingStation(int x, int y, int cx, int cy, Chunk chunk) {
		super(x, y, cx, cy, chunk);
	
		this.id = TileID.Background;
	
	}

	@Override
	public void click(GameContainer gc) {
		gc.getPlayer().getInv().open(gc);
	}

	@Override
	public Image getTexture() {
		return Textures.get("craftingstation");
	}
}
