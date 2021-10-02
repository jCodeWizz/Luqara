package dev.CodeWizz.Luqara.world.chunk;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.Luqara.objects.Rock;
import dev.CodeWizz.Luqara.objects.Tree;
import dev.CodeWizz.Luqara.world.World;
import dev.CodeWizz.Luqara.world.WorldType;
import dev.CodeWizz.Luqara.world.tiles.ITileTickable;
import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.TileID;
import dev.CodeWizz.Luqara.world.tiles.air;
import dev.CodeWizz.Luqara.world.tiles.dirt;
import dev.CodeWizz.Luqara.world.tiles.grassBlock;
import dev.CodeWizz.Luqara.world.tiles.stone;
import dev.CodeWizz.Luqara.world.tiles.water;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.util.WNoise;

public class Chunk {

	public Tile[][] tiles;
	private List<GameObject> objects = new CopyOnWriteArrayList<>();
	
	private Image texture;

	private boolean loaded;
	private boolean generated;
	private boolean update = false;
	private int counter = 0;

	private final int waterLevel = 23;
	private final int tileW = 16, tileH = 50;
	private int x;
	private int y;
	private World world;
	private WNoise noise;
	private WorldType type;

	public Chunk(GameContainer gc, World world, WNoise noise, WorldType type, int x, int y) {
		this.x = x;
		this.y = y;
		this.world = world;
		this.noise = noise;
		this.type = type;

		tiles = new Tile[tileW][tileH];

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new air(i * 16 + x, j * 16 + y, i, j, this);
			}
		}

		updateChunk(gc);
	}

	public void render(GameContainer gc, Renderer r) {
		r.drawImage(texture, x, y);

		if (GameContainer._debug)
			r.drawRect((int) x, y, tileW * 16, tileH * 16, 0xfffcec03, Light.NONE);
	}

	public void load(GameContainer gc) {
		loaded = true;
		if (generated) {
			updateChunk(gc);
		} else {
			if (type == WorldType.Normal) {
				generateNormal(gc);
			} else if (type == WorldType.Flat) {
				generateFlat(gc);
			}
		}
		
		gc.handler.object.addAll(objects);
	}

	private void generateTree(GameContainer gc, int[] y) {
		for(int i = 0; i < y.length; i++) {
			double data = noise.noise((i*16 + x) * 50);
			
			if(data > 0.15 && y[i] < waterLevel && tiles[i][y[i]].isPlantSpawnable()) {
				gc.handler.addObject(new Tree(i*16 + x + 32, y[i]*16 - 16 + this.y));
			}
			
		}
	}
	
	private void generateRock(GameContainer gc, int[] y) {
		boolean placed = false;
		for(int i = 0; i < y.length; i++) {
			double data = noise.noise((i*16 + x) * 10);
			
			
			if(data > 0.15 && y[i] < waterLevel && !placed && tiles[i][y[i]].isPlantSpawnable() && i < 14) {
				placed = true;
				
				if(tiles[i+1][y[i+1]].isPlantSpawnable() && tiles[i+2][y[i+2]].isPlantSpawnable()) {
					gc.handler.addObject(new Rock(i*16 + x, y[i]*16 - 16 + this.y));
				}
			} else if(data <= 0.15f) {
				placed = false;
			}
			
		}
	}

	private void generateFlat(GameContainer gc) {
		int[] dataList = new int[tiles.length];
		generated = true;
		if (x > 0) {
			world.addChunk(gc, x + tileW * 16, y);
		} else if (x < 0) {
			world.addChunk(gc, x - tileW * 16, y);
		}

		for (int i = 0; i < tiles.length; i++) {
			dataList[i] = 20;
			// grass
			tiles[i][20] = new grassBlock(x + i * 16, y + 20 * 16, i, 20, this);

			// dirt
			for (int j = 0; j < 5; j++) {
				tiles[i][21 + j] = new dirt(x + i * 16, y + 21 * 16 + j * 16, i, j, this);
			}

			// stone
			for (int j = 26; j < tiles[i].length; j++) {
				tiles[i][j] = new stone(x + i * 16, y + j * 16, i, j, this);
			}
		}

		generateTree(gc, dataList);
		generateRock(gc, dataList);
		updateChunk(gc);
	}

	public void update(GameContainer gc) {
		if (update) {
			updateChunk(gc);
			update = false;
		}
		
		if(counter < 60) {
			counter++;
		} else {
			tickChunk(gc);
			counter = 0;
		}
	}

	private void tickChunk(GameContainer gc) {
		Random r = new Random();
		for(Tile[] tilesa : tiles) {
			for(Tile tiles : tilesa) {
				if(tiles instanceof ITileTickable) {
					if(r.nextInt(12) == 8) {
						((ITileTickable) tiles).update(gc);
					}
				}
			}
		}
	}

	private void generateNormal(GameContainer gc) {
		int[] dataList = new int[tiles.length];
		generated = true;
		if (x > 0) {
			world.addChunk(gc, x + tileW * 16, y);
		} else if (x < 0) {
			world.addChunk(gc, x - tileW * 16, y);
		}

		for (int i = 0; i < tiles.length; i++) {
			int data = (int) (noise.noise(i + x / 16) * 12 - waterLevel * -1);
			

			if (data < 0)
				data = 0;
			
			dataList[i] = data;

			// fill in grassblocks
			if (data > waterLevel)
				tiles[i][data] = new dirt(x + i * 16, y + data * 16, i, data, this);
			else
				tiles[i][data] = new grassBlock(x + i * 16, y + data * 16, i, data, this);

			// fill in dirt layers
			for (int j = data + 1; j < data + 6; j++) {
				tiles[i][j] = new dirt(x + i * 16, y + j * 16, i, j, this);
			}

			// fill in stone layers
			for (int j = data + 6; j < tiles[i].length; j++) {
				tiles[i][j] = new stone(x + i * 16, y + j * 16, i, j, this);
			}

			// fill in water layers
			for (int j = waterLevel; j < tiles[i].length; j++) {
				if (tiles[i][j].getId() == TileID.Air)
					tiles[i][j] = new water(x + i * 16, y + j * 16, i, j, this);
			}

		}

		generateTree(gc, dataList);
		generateRock(gc, dataList);
		updateChunk(gc);

	}

	public void deload(GameContainer gc) {
		for (Tile object : gc.handler.tile) {
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {

					if (tiles[i][j].getX() == object.getX() && tiles[i][j].getY() == object.getY()) {
						gc.handler.removeTile(object);
					}
				}
			}
		}
		
		objects.clear();
		for(GameObject object : gc.handler.object) {
			if(object.getBounds().intersects(getBounds())) {
				objects.add(object);
			}
		}
		
		gc.handler.object.removeAll(objects);
		loaded = false;
	}

	public void updateChunk(GameContainer gc) {
		BufferedImage im = new BufferedImage(tileW * 16, tileH * 16, BufferedImage.TYPE_INT_ARGB);
		Graphics g = im.createGraphics();

		for (Tile object : gc.handler.tile) {
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {

					if (tiles[i][j].getX() == object.getX() && tiles[i][j].getY() == object.getY()) {
						gc.handler.removeTile(object);
					}
				}
			}
		}
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				gc.handler.addTile(tiles[i][j]);
				if (tiles[i][j].getId() != TileID.Air) {
					g.drawImage(tiles[i][j].getTexture().getImage(), i * 16, j * 16, null);
				}
			}
		}

		texture = new Image(im);
		texture.setAlpha(true);
	}
	
	public void placeTile(Tile tile) {
		if(tile.getChunkY() >= 0 && tile.getChunkX() < 16 && tile.getChunkX() >= 0) {
			tiles[tile.getChunkX()][tile.getChunkY()] = tile;
			setUpdate(true);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, tileW * 16, tileH * 16);
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public boolean isGenerated() {
		return generated;
	}

	public void setGenerated(boolean generated) {
		this.generated = generated;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}
	
	public Tile getUpperTile(int x, int y) {
		return tiles[x][y-1];
	}

}
