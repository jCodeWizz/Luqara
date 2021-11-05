package dev.CodeWizz.Luqara.items.inventories;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.Luqara.items.Inventory;
import dev.CodeWizz.Luqara.items.Recipe;
import dev.CodeWizz.Luqara.items.items.WoodLog;
import dev.CodeWizz.Luqara.items.items.WoodenPlanks;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Textures;

public class CraftingInventory extends Inventory {

	public static List<Recipe> r = new CopyOnWriteArrayList<>();

	public CraftingInventory(GameContainer gc, int size) {
		super(size);

		
		setupRecipes(gc);
	}

	public static void setupRecipes(GameContainer gc) {
		r.clear();
		
		
		// add twigs to this!
		r.add(new Recipe(new WoodenPlanks(4), "woodenplanks", new WoodLog(1)));

		for (int i = 0; i < r.size(); i++) {
			Recipe recipe = r.get(i);
			recipe.x = ((int) (gc.getWidth() / 2 - Textures.get("inventoryUI").getW() * 1.5f)) / 2 - recipe.w / 2;
			recipe.y = i * (recipe.h + 10) + 10;

		}
	}

	@Override
	public void render(GameContainer gc, Renderer re) {
		super.render(gc, re);
		for (Recipe recipe : r) {
			recipe.render(gc, re);
		}
	}

	@Override
	public void open(GameContainer gc) {
		super.open(gc);
		gc.getPlayer().getInv().open(gc);
		gc.getPlayer().getInv().setRenderInv(this);
	}
}
