package dev.CodeWizz.engine.hud;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;

public class Button implements IHudComponent {

	private int x, y, w, h;
	private String text;
	
	public Button(int x, int y, int w, int h, String text) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.text = text;
	}
	
	@Override
	public void tick(GameContainer gc) {

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawRectUI(x, y, w, h, 0xff000000, Light.NONE);
		r.drawText(text, x + w/2, y + h/2, 0xff000000, true);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}

	@Override
	public void click(GameContainer gc) {

	}
}
