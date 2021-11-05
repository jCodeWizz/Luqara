package dev.CodeWizz.Luqara.world.tiles;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.Textures;

public class ironBrazier extends Tile {

	private Light light;
	
	public ironBrazier(int x, int y, int cx, int cy, Chunk chunk) {
		super(x, y, cx, cy, chunk);
		
		id = TileID.Solid;
		this.name = "ironbrazier";
		this.lightLevel = 5;
		light = new Light(50, 0xfffcbf23);
	}

	@Override
	public Image getTexture() {
		return Textures.get("ironbrazier");
	}
	
	@Override
	public Light getLight() {
		return light;
	}
}
