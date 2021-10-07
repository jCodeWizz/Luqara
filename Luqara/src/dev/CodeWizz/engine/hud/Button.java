package dev.CodeWizz.engine.hud;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class Button implements IHudComponent {

	private String useID;
	
	public Button(String useID) {
		this.useID = useID;
		
	}
	
	@Override
	public void tick(GameContainer gc) {

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		
	}
	
	public void click() {
		
	}
}
