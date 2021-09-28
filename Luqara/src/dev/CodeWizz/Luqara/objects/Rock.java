package dev.CodeWizz.Luqara.objects;

import dev.CodeWizz.Luqara.items.Item;
import dev.CodeWizz.Luqara.items.items.SmallRock;
import dev.CodeWizz.Luqara.util.IAction;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Textures;

public class Rock extends GameObject implements IAction {

	private boolean broken;
	private int uses = 5;
	
	
	public Rock(float x, float y) {
		super(x, y);

		this.id = ID.Rock;
		this.health = 20;
		
		
	}

	@Override
	public boolean startAction(GameContainer gc) {
		if(uses > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public void endAction(GameContainer gc) {
		uses--;
		Item.add(new Item((int)x+16, (int)y-16, new SmallRock(1)));
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

	@Override
	public int getActionTime() {
		return 3;
	}

	@Override
	public int offsetX() {
		return 16;
	}

	@Override
	public int offsetY() {
		return -16;
	}
}
