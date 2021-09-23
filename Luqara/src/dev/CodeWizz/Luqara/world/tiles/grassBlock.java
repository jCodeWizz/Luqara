package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;

public class grassBlock extends Tile {

	public grassBlock(int x, int y, Chunk chunk) {
		super(x, y, chunk);
		
		this.id = TileID.Solid;
		this.plantSpawnable = true;
		
	}

	@Override
	public Image getTexture() {
		return Textures.get("grassBlock");
	}
}
