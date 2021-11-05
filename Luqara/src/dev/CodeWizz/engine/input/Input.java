package dev.CodeWizz.engine.input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.hud.HudManager;
import dev.CodeWizz.engine.hud.IHudComponent;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	private List<ITextInput> inputs = new CopyOnWriteArrayList<>();

	private GameContainer gc;

	private final int NUM_KEYS = 256;
	private boolean[] keys = new boolean[NUM_KEYS];
	private boolean[] keysLast = new boolean[NUM_KEYS];

	private final int NUM_BUTTONS = 5;
	private boolean[] buttons = new boolean[NUM_KEYS];
	private boolean[] buttonsLast = new boolean[NUM_KEYS];

	int mouseX, mouseY;
	private int scroll;

	public Input(GameContainer gc) {
		this.gc = gc;

		mouseX = 0;
		mouseY = 0;
		scroll = 0;

		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		gc.getWindow().getCanvas().addMouseWheelListener(this);

	}

	public void update() {
		scroll = 0;

		if (isButtonDown(1)) {
			for (IHudComponent x : HudManager.comps) {
				if (x.getBounds()
						.intersects(new Rectangle(mouseX - gc.camera.getX(), mouseY - gc.camera.getY(), 1, 1))) {
					x.click(gc);
					continue;
				}
			}
		}

		if (isButtonUp(1)) {
			for (IHudComponent x : HudManager.comps) {
				x.declick(gc);
				continue;
			}
		}

		for (int i = 0; i < NUM_KEYS; i++) {
			keysLast[i] = keys[i];
		}

		for (int i = 0; i < NUM_BUTTONS; i++) {
			buttonsLast[i] = buttons[i];
		}

		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();

		mouseX = (int) (b.getX() / gc.getScale() + gc.camera.getX());
		mouseY = (int) (b.getY() / gc.getScale() + gc.camera.getY());

	}

	// KEY DOWN/UP/PRESSED

	public boolean isKey(int keyCode) {
		return keys[keyCode];
	}

	public boolean isKeyUp(int keyCode) {
		return !keys[keyCode] && keysLast[keyCode];
	}

	public boolean isKeyDown(int keyCode) {
		return keys[keyCode] && !keysLast[keyCode];
	}

	// MOUSE BUTTON DOWN/UP/PRESSED

	public boolean isButton(int keyCode) {
		return buttons[keyCode];
	}

	public boolean isButtonUp(int keyCode) {
		return !buttons[keyCode] && buttonsLast[keyCode];
	}

	public boolean isButtonDown(int keyCode) {
		return buttons[keyCode] && !buttonsLast[keyCode];
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

		if ((int) (e.getKeyChar()) - 32 < 58 && (int) (e.getKeyChar()) - 32 >= 0) {
			for (ITextInput b : inputs) {
				b.charTyped(e.getKeyChar());
			}
		} else if ((int) (e.getKeyChar()) - 32 < 94 && (int) (e.getKeyChar()) - 32 >= 0) {
			for (ITextInput b : inputs) {
				b.charTyped(e.getKeyChar());
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			for (ITextInput b : inputs) {
				b.enter();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			for (ITextInput b : inputs) {
				b.removeChar();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public int getScroll() {
		return scroll;
	}

	public void addInputListener(ITextInput a) {
		inputs.add(a);
	}

	public void removeInputListener(ITextInput a) {
		inputs.remove(a);
	}
}
