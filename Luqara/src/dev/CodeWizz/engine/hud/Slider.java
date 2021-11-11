package dev.CodeWizz.engine.hud;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.UIID;
import dev.CodeWizz.engine.util.WMath;

public class Slider implements IHudComponent {

	private int x, y, w;
	private float value;
	private float oldValue;
	private boolean dragging;
	private ISliderListener listener;
	
	public Slider(int x, int y, int w, ISliderListener listener) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.value = x;
		this.listener = listener;
	}
	
	
	@Override
	public UIID getID() {
		return UIID.Slider;
	}

	@Override
	public void tick(GameContainer gc) {
		if(dragging) {
			oldValue = value;
			value = gc.getInput().getMouseX() - gc.camera.getX();
			value = WMath.clamb(value, x + w, x);
			
			if(oldValue != value) {
				listener.onSliderMove(value - x);
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRectUI(x, y, w, 5, 0xffffffff, Light.NONE);
		r.fillRectUI(x, y, (int)value-9, 5, 0xff868f8d, Light.NONE);
		r.drawRectUI(x, y, w, 5, 0xff000000, Light.NONE);
		r.fillRectUI((int) (value-1), y-1, 3, 7, 0xff000000, Light.NONE);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y-2, w, 9);
	}

	@Override
	public void click(GameContainer gc) {
		dragging = true;
	}
	
	@Override
	public void declick(GameContainer gc) {
		if(dragging) {
			listener.onSliderSet(value - x);
		}
		dragging = false;
	}


	public float getValue() {
		return value;
	}


	public void setValue(float value) {
		this.value = value;
	}
}
