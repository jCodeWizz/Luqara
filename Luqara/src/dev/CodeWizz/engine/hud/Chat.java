package dev.CodeWizz.engine.hud;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.input.ITextInput;

public class Chat implements IHudComponent, ITextInput {

	public List<IChatListener> listeners = new CopyOnWriteArrayList<>();
	public List<Line> lines = new CopyOnWriteArrayList<>();
	private int x, y;
	private boolean open;
	private String currentText = "";

	public Chat(GameContainer gc, int x, int y) {
		this.x = x;
		this.y = y;
		gc.getInput().addInputListener(this);
	}

	@Override
	public void tick(GameContainer gc) {

		for (Line line : lines) {
			if (line.timer > 0) {
				line.timer--;
			} else {
				line.render = false;
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		for (Line line : lines) {
			if (line.render || open) {
				r.fillRectUI(x, line.y + y - 41, gc.getWidth() / 3, 15, 0x64000000, Light.FULL);
				r.drawText(line.text, x + 10, line.y + y - 40, 2, 0xffffffff);
			}
		}

		if(open) {
			r.fillRectUI(x, y - 21, gc.getWidth() / 3, 15, 0x64000000, Light.FULL);
			r.drawRectUI(x, y - 21, gc.getWidth() / 3, 15, 0xffffffff, Light.FULL);
			r.drawText(currentText, x + 10, y - 20, 2, 0xffffffff);
		}
	}

	private void sendMessage() {
		addLine(currentText);
		currentText = "";
		open = false;
	}
	
	public void sendMessage(String text) {
		addLine(text);
	}

	private void addLine(String text) {
		
		for(IChatListener a : listeners) {
			if(a.onChatMessage(text)) {
				return;
			}
		}
		
		
		for (Line line : lines) {
			line.y -= 16;
		}

		lines.add(new Line(text));
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean a) {
		this.open = a;
	}

	@Override
	public void charTyped(char a) {
		if(open) {
			currentText += a;
		}
	}

	@Override
	public void enter() {
		sendMessage();
	}

	@Override
	public void removeChar() {
		if(currentText.length() != 0) {
			currentText = currentText.substring(0, currentText.length()-1);
		}
	}
}

class Line {

	public int y, timer;
	public String text;
	public boolean render;

	public Line(String text) {
		this.y = 0;
		this.text = text;
		this.render = true;
		this.timer = 60 * 5;
	}
}
