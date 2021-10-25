package dev.CodeWizz.Luqara.items;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Textures;

public class Recipe {

	public ItemStack result;
	public ItemStack[] costs;
	private String texture;
	
	public int x, y, w, h;
	
	public Recipe(ItemStack result, String texture, ItemStack... costs) {
		this.result = result;
		this.costs = costs;
		this.texture = texture;
		
		this.x = 10;
		this.w = 250;
		this.h = 50;
	}
	
	public void click(GameContainer gc) {
		for(int i = 0; i < costs.length; i++) {
			if(!gc.getPlayer().getInv().checkForItem(costs[i].type, costs[i].size)) {
				return;
			}
		}
		// all items found
		// now remove items;
		for(int i = 0; i < costs.length; i++) {
			gc.getPlayer().getInv().removeItem(costs[i].type, costs[i].size);
		}
		
		gc.getPlayer().getInv().addItem(result);
		
		return;
	}
	
	public void render(GameContainer gc, Renderer r) {
		r.drawImageUI(Textures.get("recipe_" + texture), x, y, 2);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
}
