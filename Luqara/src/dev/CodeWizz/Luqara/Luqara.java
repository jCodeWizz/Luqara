package dev.CodeWizz.Luqara;

import java.awt.event.MouseEvent;

import dev.CodeWizz.Luqara.input.CommandInput;
import dev.CodeWizz.Luqara.input.KeyInput;
import dev.CodeWizz.Luqara.input.MouseInput;
import dev.CodeWizz.Luqara.world.World;
import dev.CodeWizz.Luqara.world.WorldType;
import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;

public class Luqara extends AbstractGame {

	private Light light;
	
	public static Luqara inst;
	
	private Player player;
	private World world;
	private MouseInput minput;
	private KeyInput kinput;
	public HUD hud;
	
	public Luqara() {
		inst = this;
		light = new Light(150, 0xffe6bc05);
	}
	
	@Override
	public void init(GameContainer gc) {
		minput = new MouseInput();
		kinput = new KeyInput();
		player = new Player(gc);
		gc.setPlayer(player);
		hud = new HUD(gc);
		
		HUD.chat.listeners.add(new CommandInput(gc));
		
		world = new World(gc, WorldType.Normal);
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		player.update(gc);
		minput.update(gc);
		kinput.update(gc);
		world.update(gc);
	}
	
	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		player.renderUI(gc, r);
	}
	
	
	

	@Override
	public void render(GameContainer gc, Renderer r) {
		
		world.render(gc, r);
		player.render(gc, r);
		
		if(gc.getInput().isButton(MouseEvent.BUTTON1))
			r.drawLight(light, gc.getInput().getMouseX(), gc.getInput().getMouseY(), true);
	}
	
	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new Luqara());
		gc.start();
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public MouseInput getMinput() {
		return minput;
	}

	public void setMinput(MouseInput minput) {
		this.minput = minput;
	}
}
