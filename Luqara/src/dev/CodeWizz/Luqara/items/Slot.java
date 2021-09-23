package dev.CodeWizz.Luqara.items;

import java.awt.Rectangle;

import dev.CodeWizz.Luqara.items.items.Air;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class Slot {

	private ItemStack item;
	private int x, y;
	private boolean hovering;
	private boolean limited = false;
	
	private Type[] types;
	
	public Slot(int x, int y, Type... args) {
		item = new Air(1);
		
		this.x = x;
		this.y = y;
		
		
		if(args.length != 0) {
			limited = true;
			types = args;
		}
		
		
		
		
		
	}
	
	public void render(GameContainer gc, Renderer r) {
		if(item.getType() != Type.Air) {
			r.drawImageUI(item.getIcon(), x, y, 3);
			if(item.size > 1)
				r.drawText("" + item.size, x + 38, y + 35, 2, 0xffffffff);
			else if(item.size == 0)
				r.drawText("" + item.size, x + 38, y + 35, 2, 0xffff0000);

		}
	}
	
	public void clear() {
		item = new Air(1);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 55, 55);
	}

	public ItemStack getItem() {
		return item;
	}

	public boolean setItem(ItemStack item) {
		if(limited) {
			for(int i = 0; i < types.length; i++) {
				if(item.getType() == types[i] || item.getType() == Type.Air) {
					this.item = item;
					return true;
				}
			}
			return false;
		} else {
			this.item = item;
			return true;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
}
