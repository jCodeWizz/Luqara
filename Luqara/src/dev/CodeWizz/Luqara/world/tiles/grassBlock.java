package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;

public class grassBlock extends Tile implements ITileTickable{

	public grassBlock(int x, int y, int cx, int cy, Chunk chunk) {
		super(x, y, cx, cy, chunk);
		
		this.id = TileID.Solid;
		this.plantSpawnable = true;
		this.name = "grassBlock";
	}

	@Override
	public Image getTexture() {
		return Textures.get("grassBlock");
	}

	@Override
	public void update(GameContainer gc) {
		if(chunk.getUpperTile(chunkX, chunkY).getId() == TileID.Solid) {
			chunk.placeTile(new dirt(x, y, chunkX, chunkY, chunk));
		}
	}
}
