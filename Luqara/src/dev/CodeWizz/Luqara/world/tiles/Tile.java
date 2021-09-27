package dev.CodeWizz.Luqara.world.tiles;

import java.awt.Rectangle;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.gfx.Image;

public abstract class Tile {

	
	protected boolean plantSpawnable;
	protected int x, y;
	protected TileID id;
	protected Chunk chunk;
	protected String name;
	
	
	public Tile(int x, int y, Chunk chunk) {
		this.x = x;
		this.y = y;
		this.chunk = chunk;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15);
	}
	
	public abstract Image getTexture();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public TileID getId() {
		return id;
	}

	public void setId(TileID id) {
		this.id = id;
	}

	public boolean isPlantSpawnable() {
		return plantSpawnable;
	}

	public void setPlantSpawnable(boolean plantSpawnable) {
		this.plantSpawnable = plantSpawnable;
	}

	public Chunk getChunk() {
		return chunk;
	}

	public void setChunk(Chunk chunk) {
		this.chunk = chunk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
