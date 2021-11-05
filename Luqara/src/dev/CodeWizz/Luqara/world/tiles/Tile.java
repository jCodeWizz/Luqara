package dev.CodeWizz.Luqara.world.tiles;

import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;

import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.gfx.light.Light;

public abstract class Tile {

	
	protected boolean plantSpawnable;
	protected int x, y, chunkX, chunkY, lightLevel;
	protected TileID id;
	protected Chunk chunk;
	protected String name;
	
	
	public Tile(int x, int y, int cx, int cy, Chunk chunk) {
		this.x = x;
		this.y = y;
		this.chunkX = cx;
		this.chunkY = cy;
		this.chunk = chunk;
	}
	
	@Override
	public Tile clone() {
		try {
			Tile b = getClass().getDeclaredConstructor().newInstance();
			return b;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Light getLight() {
		return null;
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

	public int getChunkX() {
		return chunkX;
	}

	public void setChunkX(int chunkX) {
		this.chunkX = chunkX;
	}

	public int getChunkY() {
		return chunkY;
	}

	public void setChunkY(int chunkY) {
		this.chunkY = chunkY;
	}

	public int getLightLevel() {
		return lightLevel;
	}

	public void setLightLevel(int lightLevel) {
		this.lightLevel = lightLevel;
	}
}
