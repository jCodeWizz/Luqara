package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.gfx.Image;

public class air extends Tile {

	public air(int x, int y, Chunk chunk) {
		super(x, y, chunk);

		this.id = TileID.Air;
		this.name = "air";
	}

	@Override
	public Image getTexture() {
		return null;
	}
}