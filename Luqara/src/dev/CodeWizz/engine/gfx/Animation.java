package dev.CodeWizz.engine.gfx;

public class Animation {
	
	private int counter;
	private int speed, index;
	private Image[] frames;
	
	public Animation(int speed, Image... frames) {
		this.speed = speed;
		this.frames = frames;
		
		index = 0;
	}
	
	public void tick() {
		
		if(counter < speed) 
			counter++;
		else {
			if(index == frames.length-1)
				index = 0;
			else
				index++;
			counter = 0;
		}
		
		
		
	
	}
	
	public void reset() {
		index = 0;
		counter = 0;
	}
	
	public Image getFrame() {
		return frames[index];
	}
}
