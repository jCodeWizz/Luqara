package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;

public class grass extends Tile {

	public grass(int x, int y, int cx, int cy, Chunk chunk) {
		super(x, y, cx, cy, chunk);

		this.id = TileID.Background;
		this.setPlantSpawnable(true);
	}

	@Override
	public Image getTexture() {
		return Textures.get("grass");
	}
}
