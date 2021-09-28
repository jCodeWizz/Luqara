package dev.CodeWizz.Luqara.world;

import java.awt.Rectangle;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.Luqara.HUD;
import dev.CodeWizz.Luqara.items.Item;
import dev.CodeWizz.Luqara.world.chunk.Chunk;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.WNoise;

public class World {

	public List<Chunk> chunks = new CopyOnWriteArrayList<>();

	public double seed = 0;
	private WNoise noise;
	private WorldType type;
	private World world;

	public World(GameContainer gc, WorldType type) {
		this.type = type;
		seed = new Random().nextGaussian();
		noise = new WNoise(seed);
		world = this;

		chunks.add(new Chunk(gc, this, noise, type, -16 * 16, 0));
		chunks.add(new Chunk(gc, this, noise, type, 0, 0));
		chunks.add(new Chunk(gc, this, noise, type, 16 * 16, 0));
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

	public void addChunk(GameContainer gc, int x, int y) {

        Thread thread = new Thread("Chunk-gen-x" + x + "y" + y) {
        	@Override
        	public void run() {
        		chunks.add(new Chunk(gc, world, noise, type, x, y));
        	}
        };

        thread.start();

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
