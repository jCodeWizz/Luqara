package dev.CodeWizz.Luqara.objects;

import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.TileID;
import dev.CodeWizz.Luqara.world.tiles.dirt;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Textures;

public class FallingTile extends GameObject {

	private Tile tile;
	
	public FallingTile(float x, float y, Tile tile) {
		super(x, y);
		
		this.hasCollision = true;
		this.hasGravity = true;
		
		this.tile = tile;
		
		this.id = ID.FallingTile;
		
		this.tileCollisionID.add(TileID.Solid);
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(Textures.get(tile.getName()), (int)x, (int)y);
	}
	
	@Override
	public void collided(GameContainer gc, Tile tile) {
		tile.getChunk().placeTile(tile.getX(), tile.getY() - 16, new dirt(tile.getX(), tile.getY() - 16, tile.getChunk()));
		gc.handler.removeObject(this);
	}
	
	@Override
	public void tick(GameContainer gc) {
		x+=velX;
		y+=velY;
		super.tick(gc);
	}
}
