package dev.CodeWizz.engine;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import dev.CodeWizz.Luqara.Player;
import dev.CodeWizz.engine.gfx.Camera;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.input.Input;
import dev.CodeWizz.engine.object.Handler;
import dev.CodeWizz.engine.util.NormalMaps;
import dev.CodeWizz.engine.util.Sounds;
import dev.CodeWizz.engine.util.Textures;

public class GameContainer implements Runnable {

	public static boolean _debug = false;

	private Thread thread;
	private Input input;
	private Window window;
	private Renderer renderer;
	private AbstractGame game;

	public Camera camera;
	public Handler handler;
	private Player player;
	private Textures textures;
	private Sounds sounds;
	private NormalMaps normalMaps;
	
	public static int loaderTime = 0;
	
	private boolean running = false;
	private final double UPDATE_CAP = 1.0 / 60.0;

	private final float scale = 2f;
	private int width = (int) (1920 / scale), height = (int) (1080 / scale);

	private final String title = "Luqara Engine v0.0.0.0.1";

	public GameContainer(AbstractGame game) {
		this.game = game;
		
		width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / scale);
		height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / scale);

	}

	public void start() {
		window = new Window(this);
		loaderTime = 10;
		renderer = new Renderer(this);
		textures = new Textures();
		sounds = new Sounds();
		normalMaps = new NormalMaps();
		loaderTime = 25;
		input = new Input(this);
		loaderTime = 30;
		handler = new Handler();
		loaderTime = 40;
		camera = new Camera();
		loaderTime = 50;
		
		textures.load();
		sounds.load();
		normalMaps.load();
		
		game.init(this);
		
		
		loaderTime = 100;

		thread = new Thread(this);
		thread.run();
		
	}

	

	public void stop() {

	}
	
	public void update() {
		if(input.isKeyDown(KeyEvent.VK_F3)) {
			if(_debug) 
				_debug = false;
			else
				_debug = true;
		}
	}

	public void run() {
		running = true;

		boolean render = false;
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;

		double frameTime = 0;
		int frames = 0;
		int fps = 0;

		while (running) {
			render = false;

			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;

			unprocessedTime += passedTime;
			frameTime += passedTime;

			while (unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				render = true;

				update();
				game.update(this, (float) UPDATE_CAP);
				handler.tick(this);
				camera.update(this);

				input.update();

				if (frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
					System.out.println("[ FPS ]: " + fps);
				}
			}

			if (render) {
				renderer.clear();
				game.render(this, renderer);
				handler.render(this, renderer);

				renderer.process();
				
				game.renderUI(this, renderer);

				if(loaderTime < 100) {
					renderer.fillRect(0, 0, width, height, 0xffccffff, Light.NONE);
				}
				
				window.update();

				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		dispose();
	}
	
	public static void showInfo() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();

		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println();
		
		for (int i = 0; i < gs.length; i++) {
			DisplayMode dm = gs[i].getDisplayMode();
			if(gs.length == 1) {
				System.out.println("[System]: Screen Refreshrate = " + dm.getRefreshRate() + "Hz || Screen Resolution = " + dm.getWidth() + "x" + dm.getHeight());
			} else {
				System.out.println("[System]: Screen [" + (i+1) + "] Refreshrate = " + dm.getRefreshRate() + "Hz || Screen Resolution = " + dm.getWidth() + "x" + dm.getHeight());
			}
		}
		
		System.out.println("[System]: Operating System: " + System.getProperty("os.name"));
	 	System.out.println("[System]: Java Version: " + System.getProperty("java.version"));
		System.out.println("[System]: Main Path = " + System.getProperty("sun.java.command") + ".java");
		
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println();
	}
	
	public Renderer getRenderer() {
		return renderer;
	}
	
	public Player getPlayer() {
		return player;
	}

	public Input getInput() {
		return input;
	}

	private void dispose() {

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getScale() {
		return scale;
	}

	public String getTitle() {
		return title;
	}

	public Window getWindow() {
		return window;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}