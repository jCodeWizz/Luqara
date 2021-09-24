package dev.CodeWizz.engine.hud;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class Chat implements IHudComponent {

	public List<Line> lines = new CopyOnWriteArrayList<>();
	private int x, y;

	public Chat() {

	}

	@Override
	public void tick(GameContainer gc) {

		for(Line line : lines) {
			if(line.timer > 0) {
				line.timer--;
			} else {
				lines.remove(line);
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		for(Line line : lines) {
			if(line.render) {
				r.drawText(line.text, line.x + x, line.y + y, 0xffffffff);
			}
		}
	}
	
	public void addLine(GameContainer gc, String text) {
		for(Line line : lines) {
			line.y-=10;
		}
		
		lines.add(new Line(10, gc.getHeight()-20, text));
	}

}

class Line {

	public int x, y, timer;
	public String text;
	public boolean render;

	public Line(int x, int y, String text) {
		this.x = x;
		this.text = text;
		this.render = true;
	}
}
