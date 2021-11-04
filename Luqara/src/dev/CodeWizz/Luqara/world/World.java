package dev.CodeWizz.Luqara.world;

import java.awt.Rectangle;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.Luqara.items.Item;
import dev.CodeWizz.Luqara.util.HUD;
import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.Luqara.world.chunk.ChunkRequest;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.IRandomTickable;
import dev.CodeWizz.engine.util.WNoise;

public class World{

	public List<Chunk> chunks = new CopyOnWriteArrayList<>();
	public List<GameObject> passiveMobs = new CopyOnWriteArrayList<>();
	public List<GameObject> agressiveMobs = new CopyOnWriteArrayList<>();
	
	public List<ChunkRequest> rs = new CopyOnWriteArrayList<>();

	private static Thread chunkGenerationThread;
	
	
	public double seed = 0;
	private WNoise noise;
	private WorldType type;
	private World world;
	private int counter = 0;
	
	
	public boolean isOpen;
	
	private int passiveMobCap = 20;
	private int agressiveMobCap = 10;

	public World(GameContainer gc, WorldType type) {
		this.type = type;
		seed = new Random().nextGaussian();
		noise = new WNoise(seed);
		world = this;

		chunks.add(new Chunk(gc, this, noise, type, -16 * 16, 0, false));
		chunks.add(new Chunk(gc, this, noise, type, 0, 0, false));
		chunks.add(new Chunk(gc, this, noise, type, 16 * 16, 0, false));
		
		isOpen = true;
		
		chunkGenerationThread = new Thread(new Runnable() {
			public void run() {
				while(isOpen) {
					if(!rs.isEmpty()) {
						for(ChunkRequest r : rs) {
							chunks.add(new Chunk(r.gc, world, noise, type, r.x, r.y, true));
							rs.remove(r);
						}
					}
					
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "chunk-gen-thread");
		
		chunkGenerationThread.start();
	}

	public void update(GameContainer gc) {

		for (Chunk chunk : chunks) {
			if (chunk.getBounds().intersects(new Rectangle(0 + gc.getRenderer().getCamX(),
					0 + gc.getRenderer().getCamY(), gc.getWidth(), gc.getHeight()))) {
				if (!chunk.isLoaded()) {
					chunk.load(gc);
				}
			} else {
				if (chunk.isLoaded()) {
					chunk.deload(gc);
				}
			}

			if (chunk.isLoaded())
				chunk.update(gc);
		}
		
		for(GameObject object : gc.handler.object) {
			object.setCanMove(object.getBounds().intersects(new Rectangle(gc.camera.getX(), gc.camera.getY(), gc.getWidth(), gc.getHeight())));
		}
		
		if(counter < 60) {
			counter++;
		} else {
			counter = 0;
			if(gc.handler.object.size() > 0) {
				GameObject obj = gc.handler.object.get(new Random().nextInt(gc.handler.object.size()));
				if(obj instanceof IRandomTickable)
					((IRandomTickable) obj).trigger(gc);
			}
		}

		Item.updateAll(gc);

	}

	public void sendInfoInChat(GameContainer gc) {
		HUD.chat.sendMessage("Tile List Size: " + gc.handler.tile.size());
		HUD.chat.sendMessage("Object List Size: " + gc.handler.object.size());
		HUD.chat.sendMessage("Chunks List Size: " + chunks.size());
	}

	public void render(GameContainer gc, Renderer r) {
		for (Chunk chunk : chunks) {
			if (chunk.isLoaded()) {
				chunk.render(gc, r);
			}
		}

		Item.renderAll(gc, r);

	}
	
	public GameObject spawnEntity(GameContainer gc, GameObject e, boolean force) {
		if(force) {
			if(e.hasTag("agressive")) {
				agressiveMobs.add(e);
				gc.handler.addObject(e);
			} else if(e.hasTag("passive")) {
				passiveMobs.add(e);
				gc.handler.addObject(e);
			}
		} else {
			if(e.hasTag("agressive")) {
				if(agressiveMobs.size() < agressiveMobCap) {
					agressiveMobs.add(e);
					gc.handler.addObject(e);
				}
			} else if(e.hasTag("passive")) {
				if(e.hasTag("passive")) {
					if(passiveMobs.size() < passiveMobCap) {
						passiveMobs.add(e);
						gc.handler.addObject(e);
					}
				}
			}
				
		}
		return e;
	}

	public void addChunk(GameContainer gc, int x, int y) {

		rs.add(new ChunkRequest(gc, x, y));
	}

	public List<Chunk> getChunks() {
		return chunks;
	}

	public void setChunks(List<Chunk> chunks) {
		this.chunks = chunks;
	}

	public double getSeed() {
		return seed;
	}

	public void setSeed(double seed) {
		this.seed = seed;
	}

	public WorldType getType() {
		return type;
	}

	public void setType(WorldType type) {
		this.type = type;
	}
}
