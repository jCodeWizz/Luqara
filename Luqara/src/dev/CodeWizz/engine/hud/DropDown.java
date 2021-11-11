package dev.CodeWizz.engine.hud;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.UIID;

public class DropDown implements IHudComponent {
	
	private int x, y, w, fw, selectedOption;
	private String[] options;
	private boolean open, justOpened;
	private IDropDownListener listener;
	
	public DropDown(int x, int y, int w, int ao, IDropDownListener listener) {
		this.x = x;
		this.y = y;
		this.w = w;
		options = new String[ao];
		this.listener = listener;
		
		fw = 16 + options.length*10;
	}
	
	
	@Override
	public UIID getID() {
		return UIID.DropDown;
	}

	@Override
	public void tick(GameContainer gc) {
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
			r.fillRectUI(x, y, w, 16, 0xff005f55, Light.NONE);
		if(open) {
			for(int i = 0; i < options.length; i++) {
				r.fillRectUI(x, y + i*10 + 16, w, 10, 0xffff0000, Light.NONE);
				r.drawRectUI(x, y + i*10 + 16, w, 10, 0xff000000, Light.NONE);
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		if(open) {
			return new Rectangle(x, y, w, fw);
		} else {
			return new Rectangle(x, y, w, 16);
		}
	}

	@Override
	public void click(GameContainer gc) {
		if(!open) {
			open = true;
			justOpened = true;
		} else {
			for(int i = 0; i < options.length; i++) {
				if(new Rectangle(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY(), 1, 1).intersects(new Rectangle(x, y + i*10 + 16, w, 10))) {
					selectedOption = i;
					listener.onDropDownSet(selectedOption, options[selectedOption]);
					continue;
				}
			}
		}
	}

	@Override
	public void declick(GameContainer gc) {
		if(open && !justOpened)
			open = false;
		else if(justOpened) {
			justOpened = false;
		}
		
	}
}
