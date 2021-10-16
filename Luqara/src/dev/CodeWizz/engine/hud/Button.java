package dev.CodeWizz.engine.hud;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;

public class Button implements IHudComponent {

	private IButtonListener l;
	private int x, y, w, h;
	
	public Button(int x, int y, int w, int h, String text, IButtonListener l) {
		this.l = l;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	@Override
	public void tick(GameContainer gc) {

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawRectUI(x, y, h, w, 0xff000000, Light.NONE);
	}
	
	@Override
	public Rectangle getBounds(GameContainer gc) {
		return new Rectangle(x, y, w, h);
	}

	@Override
	public void click(GameContainer gc) {
		l.click();
	}
}
