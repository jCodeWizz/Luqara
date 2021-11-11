package dev.CodeWizz.Luqara;

import dev.CodeWizz.Luqara.input.CommandInput;
import dev.CodeWizz.Luqara.input.KeyInput;
import dev.CodeWizz.Luqara.input.MouseInput;
import dev.CodeWizz.Luqara.util.HUD;
import dev.CodeWizz.Luqara.world.World;
import dev.CodeWizz.Luqara.world.WorldType;
import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.hud.Button;
import dev.CodeWizz.engine.hud.DropDown;
import dev.CodeWizz.engine.hud.IDropDownListener;
import dev.CodeWizz.engine.hud.ISliderListener;
import dev.CodeWizz.engine.hud.Slider;

public class Luqara extends AbstractGame implements IDropDownListener, ISliderListener{

	
	public static Luqara inst;
	
	private Player player;
	private World world;
	private MouseInput minput;
	private KeyInput kinput;
	public HUD hud;
	
	public Luqara() {
		inst = this;
	}
	
	@Override
	public void init(GameContainer gc) {
		minput = new MouseInput();
		kinput = new KeyInput();
		player = new Player(gc);
		gc.setPlayer(player);
		hud = new HUD(gc);
		
		HUD.chat.listeners.add(new CommandInput(gc));

		hud.addButton(new Button(gc.getWidth()/2-75, gc.getHeight()/2-75, 150, 50, "Singleplayer") {
			@Override
			public void click(GameContainer gc) {
				world = new World(gc, WorldType.Normal, "New World");
				Player._ENABLED = true;
				hud.clear(HUD.ALL);
			}
		});
		
		hud.addButton(new Button(gc.getWidth()/2-75, gc.getHeight()/2-25 + 50, 150, 50, "Quit") {
			@Override
			public void click(GameContainer gc) {
				System.exit(0);
			}
		});
		
		hud.addComp(new DropDown(10, 10, 96, 5, this));
		hud.addComp(new Slider(10, 100, 100, this));
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		player.update(gc);
		minput.update(gc);
		kinput.update(gc);
		
		if(world != null) {
			world.update(gc);
		}
	}
	
	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		if(MouseInput.mouseTile != null) {
			r.fillRect(MouseInput.mouseTile.getX(), MouseInput.mouseTile.getY(), 15, 15, 0x64000000, Light.NONE);
		}
		
		if(!Player._ENABLED) {
			r.fillRectUI(0, 0, gc.getWidth(), gc.getHeight(), 0xff61e8ae, Light.NONE);
		}
		
		player.renderUI(gc, r);
		hud.render(gc, r);
	}
	
	

	@Override
	public void render(GameContainer gc, Renderer r) {
		if(world != null) {
			world.render(gc, r);
		}
		player.render(gc, r);
		
		if(gc.getInput().isButton(1)) {
			r.drawLight(MouseInput.light, gc.getInput().getMouseX(), gc.getInput().getMouseY(), true);
		}
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

	@Override
	public void onDropDownSet(int i, String string) {
		
	}

	@Override
	public void onSliderSet(float value) {
		
	}

	@Override
	public void onSliderMove(float value) {
		
	}
}
