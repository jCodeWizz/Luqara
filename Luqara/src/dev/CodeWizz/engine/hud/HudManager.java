package dev.CodeWizz.engine.hud;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.UIID;

public class HudManager {

	public static List<IHudComponent> comps = new CopyOnWriteArrayList<>();
	
	
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
	
	public void addComponent(IHudComponent a) {
		comps.add(a);
	}
	
	public void removeComponent(IHudComponent a) {
		comps.remove(a);
	}
	
	public void clear() {
		comps.clear();
	}
	
	public void clear(UIID id) {
		for(IHudComponent comp : comps) {
			if(comp.getID() == id) {
				comps.remove(comp);
			}
		}
	}
}
