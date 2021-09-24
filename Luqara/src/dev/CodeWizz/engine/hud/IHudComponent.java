package dev.CodeWizz.engine.hud;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public interface IHudComponent {

	public void tick(GameContainer gc);
	public void render(GameContainer gc, Renderer r);
	
}
