package dev.CodeWizz.Luqara.items;

import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.Luqara.world.tiles.TileID;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class Item {

	private boolean falling;
	private float velY;
	private float x, y;

	private int cooldownTimer = 0;

	private ItemStack itemStack;
	
	public static List<Item> items = new CopyOnWriteArrayList<>();

	public Item(float x, float y, ItemStack itemStack, int cooldownTimer) {
		this.itemStack = itemStack;

		this.x = x;
		this.y = y;

		this.cooldownTimer = cooldownTimer;
	}
	
	public Item(float x, float y, ItemStack itemStack) {
		this.itemStack = itemStack;

		this.x = x;
		this.y = y;

	}
	
	public static void renderAll(GameContainer gc, Renderer r) {
		for(Item item : items) {
			item.render(gc, r);
		}
	}
	
	public static void updateAll(GameContainer gc) {
		for(Item item : items) {
			item.update(gc);
		}
	}

	public void render(GameContainer gc, Renderer r) {
		r.drawImage(itemStack.getIcon(), (int)x, (int)y);
	}

	public void update(GameContainer gc) {

		if(cooldownTimer > 0)
			cooldownTimer--;

		y += velY;
		if (falling) {
			if (velY < 15 || velY > 0) {
				velY += 0.15;
			}
		}

		for (Tile tile : gc.handler.tile) {

			if (tile.getId() == TileID.Solid && tile.getBounds().intersects(this.getBounds())) {
				falling = false;
				y = tile.getY() - 16;
				velY = 0;
				continue;
			} else {
				falling = true;
			}
		}
	}
	
	public static void add(Item item) {
		items.add(item);
	}
	
	public static void remove(Item item) {
		items.remove(item);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

	public ItemStack getItems() {
		return itemStack;
	}

	public void setItems(ItemStack itemStack) {
		this.itemStack = itemStack;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean onCooldown() {
		return cooldownTimer > 0;
	}

}
