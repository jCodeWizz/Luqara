package dev.CodeWizz.Luqara.items.inventories;

import java.awt.Rectangle;

import dev.CodeWizz.Luqara.input.MouseInput;
import dev.CodeWizz.Luqara.items.Inventory;
import dev.CodeWizz.Luqara.items.Slot;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Textures;

public class PlayerInventory extends Inventory {

	private Slot weaponSlot;
	private Slot toolSlot;
	private Slot helmetSlot;
	private Slot armourSlot;
	private Slot currentSlot;

	private int w, h;

	private Inventory renderInv;

	//private int scrollY;

	private boolean showRecipes = false;

	//private List<CraftingRecipe> recipes = new CopyOnWriteArrayList<>();

	public PlayerInventory(GameContainer gc, int size) {
		super(size);

		//setupRecipes();

		w = gc.getWidth();
		h = gc.getHeight();

		currentSlot = new Slot(w / 2 - 148, h / 2 - 144);
		weaponSlot = new Slot(w / 2 - 148, h / 2 - 84, Type.SharpenedStick);
		toolSlot = new Slot(w / 2 - 148, h / 2 - 24, Type.BasicAxe, Type.SharpRock, Type.BasicAxe);
		helmetSlot = new Slot(w / 2 - 148, h / 2 + 36, new Type[] {});
		armourSlot = new Slot(w / 2 - 148, h / 2 + 96, new Type[] {});

		for (int i = 0; i < 4; i++) {
			this.slots[i].setX(w / 2 - 79);
			this.slots[i].setY(h / 2 - 84 + i * 60);
		}

		for (int i = 0; i < 4; i++) {
			this.slots[i + 4].setX(w / 2 - 19);
			this.slots[i + 4].setY(h / 2 - 84 + i * 60);
		}

		for (int i = 0; i < 4; i++) {
			this.slots[i + 8].setX(w / 2 + 41);
			this.slots[i + 8].setY(h / 2 - 84 + i * 60);
		}

		for (int i = 0; i < 4; i++) {
			this.slots[i + 12].setX(w / 2 + 101);
			this.slots[i + 12].setY(h / 2 - 84 + i * 60);
		}

	}

	/**
	public void scroll(int value) {
		if (scrollY + value <= 0) {
			scrollY += value;
			for (CraftingRecipe recipe : recipes) {
				recipe.setY(recipe.getY() + value);
			}
		}
	}
	**/
	

	@Override
	public void open(GameContainer gc) {

		/**
		if (showRecipes) {
			Game.instance.handler.addObject(new CraftButton(w / 2 - Textures.craftButton.getWidth() * 2,
					h - Textures.craftButton.getHeight() * 4 - 10, ID.Button));
		}
		**/
		
		allSlots.add(currentSlot);
		allSlots.add(weaponSlot);
		allSlots.add(toolSlot);
		allSlots.add(helmetSlot);
		
		allSlots.add(MouseInput.mouseSlot);

		allSlots.add(armourSlot);

		super.open(gc);
	}

	@Override
	public void close() {
		/**
		showRecipes = false;
		CraftButton.selected = null;
		for (GameObject button : Game.instance.handler.object) {
			if (button.getId() == ID.Button) {
				Game.instance.handler.removeObject(button);
			}
		}
		**/

		allSlots.remove(currentSlot);
		allSlots.remove(weaponSlot);
		allSlots.remove(toolSlot);
		allSlots.remove(helmetSlot);
		allSlots.remove(armourSlot);
		
		allSlots.remove(MouseInput.mouseSlot);

		if (getRenderInv() != null) {
			getRenderInv().close();
			setRenderInv(null);
		}

		/**
		for (CraftingRecipe recipe : recipes) {
			recipe.setSelected(false);
		}
		**/

		super.close();
	}
	
	@Override
	public void clear() {
		currentSlot.clear();
		weaponSlot.clear();
		toolSlot.clear();
		helmetSlot.clear();
		armourSlot.clear();
		
		
		
		super.clear();
	}
	
	
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		if(open) {
			r.drawImageUI(Textures.get("inventoryUI"), gc.getWidth()/2 - (int) (Textures.get("inventoryUI").getW()*1.5), gc.getHeight()/2 - (int)(Textures.get("inventoryUI").getH()*1.5), 3);
			
			super.render(gc, r);
			
			
			if(renderInv != null)
				renderInv.render(gc, r);
			
			currentSlot.render(gc, r);
			weaponSlot.render(gc, r);
			toolSlot.render(gc, r);
			helmetSlot.render(gc, r);
			armourSlot.render(gc, r);
			
			
			MouseInput.mouseSlot.render(gc, r);
		}
		
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(200, 0, w - 400, h);

	}

	public Slot[] getSlots() {
		return slots;

	}

	public Slot getWeaponSlot() {
		return weaponSlot;
	}

	public void setWeaponSlot(Slot weaponSlot) {
		this.weaponSlot = weaponSlot;
	}

	public Slot getToolSlot() {
		return toolSlot;
	}

	public void setToolSlot(Slot toolSlot) {
		this.toolSlot = toolSlot;
	}

	public Slot getHelmetSlot() {
		return helmetSlot;
	}

	public void setHelmetSlot(Slot helmetSlot) {
		this.helmetSlot = helmetSlot;
	}

	public Slot getArmourSlot() {
		return armourSlot;
	}

	public void setArmourSlot(Slot armourSlot) {
		this.armourSlot = armourSlot;
	}

	public Slot getCurrentSlot() {
		return currentSlot;
	}

	public void setCurrentSlot(Slot currentSlot) {
		this.currentSlot = currentSlot;
	}

	public boolean isShowRecipes() {
		return showRecipes;
	}

	public void setShowRecipes(boolean showRecipes) {
		this.showRecipes = showRecipes;
	}

	/**
	public List<CraftingRecipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<CraftingRecipe> recipes) {
		this.recipes = recipes;
	}

	private void setupRecipes() {
		recipes.add(new CraftingRecipe(10, 10, new CrateItem(), new Cost(Type.WoodLog, 5)));
		recipes.add(new CraftingRecipe(10, 10, new SharpenedStick(), new Cost(Type.WoodLog, 2),
				new Cost(Type.SmallRock, 2)));
		recipes.add(new CraftingRecipe(10, 10, new BasicAxe(), new Cost(Type.WoodLog, 2), new Cost(Type.SmallRock, 3))); // add
																															// twine
		recipes.add(
				new CraftingRecipe(10, 10, new BasicPickaxe(), new Cost(Type.WoodLog, 2), new Cost(Type.SmallRock, 3))); // add
																															// twine
		recipes.add(new CraftingRecipe(10, 10, new TwigBasket(), new Cost(Type.WoodLog, 1)));

		for (int i = 0; i < recipes.size(); i++) {
			recipes.get(i).setX(10);
			recipes.get(i).setY(10 + 110 * i);

		}

	}
	**/

	public Inventory getRenderInv() {
		return renderInv;
	}

	public void setRenderInv(Inventory renderInv) {
		this.renderInv = renderInv;
	}
}
