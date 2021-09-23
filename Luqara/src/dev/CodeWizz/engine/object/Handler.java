package dev.CodeWizz.engine.object;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.particles.Particle;

public class Handler {

	public List<GameObject> object = new CopyOnWriteArrayList<>();
	public List<Tile> tile = new CopyOnWriteArrayList<>();

	
	public void tick(GameContainer gc) {
		for(GameObject object : object) {
			object.tick(gc);
		}
		
		for(Particle p : Particle.list) {
			p.update(gc);
		}
	}
	
	public void render(GameContainer gc, Renderer r) {
		for(GameObject object : object) {
			object.render(gc, r);
		}
		
		for(Particle p : Particle.list) {
			p.render(gc, r);
		}
	}
	
	public GameObject addObject(GameObject object) {
		this.object.add(object);
		return object;
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public Tile addTile(Tile object) {
		this.tile.add(object);
		return object;
	}
	
	public Tile removeTile(Tile object) {
		this.tile.remove(object);
		return object;
	}	
}
