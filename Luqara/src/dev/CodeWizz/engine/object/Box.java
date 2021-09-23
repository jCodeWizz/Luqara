package dev.CodeWizz.engine.object;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.particles.Particle;

public class Box extends GameObject {

	private int color, lightblock;
	
	public Box(float x, float y, float w, float h, int color, int lightblock) {
		super(x, y);

		this.id = ID.Box;
		
		this.w = w;
		this.h = h;
		
		this.color = color;
		this.lightblock = lightblock;
		
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)x, (int)y, (int)w, (int)h, color, lightblock);
	}
	
	@Override
	public void destroy(GameContainer gc) {
		for(int xx = 0; xx < w/4; xx++) {
			for(int yy = 0; yy < h/4; yy++) {
				Particle.add(new Particle((int)x + xx*4, (int)y + yy*4, 0xffffffff, 4, 100, 2));
			}
		}
		
		gc.handler.removeObject(this);
	}
}
