package dev.CodeWizz.engine.gfx;

import dev.CodeWizz.engine.GameContainer;

public class Camera {

	private int x, y;
	
	public void update(GameContainer gc) {
		x = (int) (gc.getPlayer().getX() - gc.getWidth()/2 + gc.getPlayer().getW() /2);
		y = (int) (gc.getPlayer().getY() - gc.getHeight()/2 + gc.getPlayer().getH() /2);
		
		gc.getRenderer().setCamX(x);
		gc.getRenderer().setCamY(y);
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
	
}
