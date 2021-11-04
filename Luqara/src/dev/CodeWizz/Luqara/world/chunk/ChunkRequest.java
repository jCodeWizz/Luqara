package dev.CodeWizz.Luqara.world.chunk;

import dev.CodeWizz.engine.GameContainer;

public class ChunkRequest {

	public GameContainer gc;
	public int x, y;
	
	public ChunkRequest(GameContainer gc, int x, int y) {
		this.gc = gc;
		this.x = x;
		this.y = y;
	}
}
