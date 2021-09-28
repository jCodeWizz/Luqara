package dev.CodeWizz.Luqara.objects;

import java.awt.Rectangle;

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
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x+4, (int)y+2, (int) (w/2)-4, (int) h - 4);
	}

	@Override
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x + (int) (w/2), (int)y+2, (int) (w/2)-4, (int) h - 4);
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
