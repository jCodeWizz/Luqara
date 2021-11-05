package dev.CodeWizz.engine.hud;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.UIID;

public interface IHudComponent {

	public UIID getID();
	public void tick(GameContainer gc);
	public void render(GameContainer gc, Renderer r);
	public Rectangle getBounds();
	public void click(GameContainer gc);
	public void declick(GameContainer gc);
	
}
