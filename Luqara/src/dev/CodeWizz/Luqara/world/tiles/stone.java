package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;

public class stone extends Tile {

	public stone(int x, int y, int cx, int cy, Chunk chunk) {
		super(x, y, cx, cy, chunk);
		
		this.id = TileID.Solid;
		this.name = "stone";
		
	}

	@Override
	public Image getTexture() {
		return Textures.get("stone");
	}
}
