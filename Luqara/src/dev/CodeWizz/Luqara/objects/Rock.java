package dev.CodeWizz.Luqara.objects;

import dev.CodeWizz.Luqara.util.IAction;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Textures;

public class Rock extends GameObject implements IAction {

	private boolean broken;
	
	
	public Rock(float x, float y) {
		super(x, y);

		this.id = ID.Rock;
		this.health = 3;
		
		
	}

	@Override
	public void startAction(GameContainer gc) {
		broken = true;
	}

	@Override
	public void endAction(GameContainer gc) {
		health--;
		
	}

	@Override
	public void stopAction(GameContainer gc) {
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		if(broken)
			r.drawImage(Textures.get("rockBroken"), (int)x, (int)y-16);
		else
			r.drawImage(Textures.get("rock"), (int)x, (int)y-16);
	}
}
