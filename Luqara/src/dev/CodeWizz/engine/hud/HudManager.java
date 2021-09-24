package dev.CodeWizz.engine.hud;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class HudManager {

	public List<IHudComponent> comps = new CopyOnWriteArrayList<>();
	
	
	public void update(GameContainer gc) {
		for(IHudComponent a : comps) {
			a.tick(gc);
		}
	}
	
	public void render(GameContainer gc, Renderer r) {
		for(IHudComponent a : comps) {
			a.render(gc, r);
		}
	}
}
