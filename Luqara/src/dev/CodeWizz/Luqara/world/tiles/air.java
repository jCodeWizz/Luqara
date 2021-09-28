package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.gfx.Image;

public class air extends Tile {

	public air(int x, int y, int cx, int cy, Chunk chunk) {
		super(x, y, cx, cy, chunk);

		this.id = TileID.Air;
		this.name = "air";
	}

	@Override
	public Image getTexture() {
		return null;
	}
}