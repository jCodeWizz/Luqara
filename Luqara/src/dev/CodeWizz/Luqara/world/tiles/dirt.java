package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;

public class dirt extends Tile {

	public dirt(int x, int y, int cx, int cy, Chunk chunk) {
		super(x, y, cx, cy, chunk);
		
		id = TileID.Solid;
		this.name = "dirt";
	}

	@Override
	public Image getTexture() {
		return Textures.get("dirt");
	}
}
