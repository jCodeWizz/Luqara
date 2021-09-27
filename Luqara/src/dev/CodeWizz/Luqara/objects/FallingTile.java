package dev.CodeWizz.Luqara.objects;

import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.TileID;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.util.Textures;

public class FallingTile extends GameObject {

	private String tileName;
	
	public FallingTile(float x, float y, String tileName) {
		super(x, y);
		
		this.tileName = tileName;

		this.hasCollision = true;
		this.hasGravity = true;
		
		this.tileCollisionID.add(TileID.Solid);
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(Textures.get(tileName), (int)x, (int)y);
	}
	
	@Override
	public void collided(Tile tile) {
		//TODO: MAKE A PLACE TILE FUNCTION IN CHUNK! DONT FORGET TO REDRAW!!
	}
	
	@Override
	public void tick(GameContainer gc) {
		x+=velX;
		y+=velY;
		super.tick(gc);
	}
}
