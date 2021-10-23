package dev.CodeWizz.Luqara.items.inventories;

import dev.CodeWizz.Luqara.items.Inventory;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Textures;

public class CrateInventory extends Inventory {

	public CrateInventory(int size) {
		super(size);
		
		for(int i = 0; i < 5; i++) {
			slots[i].setX(GameContainer.inst.getWidth()/2-61 + 60*i);
			slots[i].setY(GameContainer.inst.getHeight()/2-234);
		}
		
		for(int i = 0; i < 4; i++) {
			slots[i+4].setX(GameContainer.inst.getWidth()/2-61 + 60*i);
			slots[i+4].setY(GameContainer.inst.getHeight()/2-174);
		}
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		if(open) {
			
			r.drawImageUI(Textures.get("crateUI"), (int) (GameContainer.inst.getWidth()/2 - Textures.get("crateUI").getW()*1.5f) + 53, (int) (GameContainer.inst.getHeight()/2 - Textures.get("crateUI").getH()*1.5f) - 180, 3);
			
			super.render(gc, r);
		}
	}
	
	@Override
	public void open(GameContainer gc) {
		super.open(gc);
		gc.getPlayer().getInv().open(gc);
		gc.getPlayer().getInv().setRenderInv(this);
	}
}
