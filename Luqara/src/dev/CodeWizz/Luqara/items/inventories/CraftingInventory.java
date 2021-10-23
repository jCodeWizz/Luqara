package dev.CodeWizz.Luqara.items.inventories;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.Luqara.items.Inventory;
import dev.CodeWizz.Luqara.items.Recipe;
import dev.CodeWizz.Luqara.items.items.SharpenedStick;
import dev.CodeWizz.Luqara.items.items.SmallRock;
import dev.CodeWizz.Luqara.items.items.WoodLog;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class CraftingInventory extends Inventory {

	public static List<Recipe> craftingRecipes = new CopyOnWriteArrayList<>();
	
	public CraftingInventory(int size) {
		super(size);

		
		// add twigs to this!
		craftingRecipes.add(new Recipe(new SharpenedStick(1), new WoodLog(4), new SmallRock(1)));
		
		
		
	}
	
	
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		super.render(gc, r);
		for(Recipe recipe : craftingRecipes) {
			recipe.render(gc, r);
		}
	}
	
	@Override
	public void open(GameContainer gc) {
		super.open(gc);
		gc.getPlayer().getInv().open(gc);
		gc.getPlayer().getInv().setRenderInv(this);
	}
}
