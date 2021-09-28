package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;

public class water extends Tile {

	public water(int x, int y, int cx, int cy, Chunk chunk) {
		super(x, y, cx, cy, chunk);
		
		this.id = TileID.Water;
		this.name = "water";
		
	}

	@Override
	public Image getTexture() {
		return Textures.get("water");
	}
}
