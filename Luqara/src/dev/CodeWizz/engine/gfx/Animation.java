package dev.CodeWizz.engine.gfx;

public class Animation {
	
	private int counter;
	private int speed, index;
	private Image[] frames;
	private boolean justRestarted;
	
	public Animation(int speed, Image... frames) {
		this.speed = speed;
		this.frames = frames;
		
		index = 0;
	}
	
	public void tick() {
		
		if(counter < speed) 
			counter++;
		else {
			if(index == frames.length-1) {
				index = 0;
				justRestarted = true;
			} else
				index++;
			counter = 0;
		}
		
		
		
	
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getLength() {
		return frames.length;
	}
	
	public boolean hasCycled() {
		return justRestarted;
	}
	
	public void reset() {
		index = 0;
		justRestarted = false;
		counter = 0;
	}
	
	public Image getFrame() {
		return frames[index];
	}
}
