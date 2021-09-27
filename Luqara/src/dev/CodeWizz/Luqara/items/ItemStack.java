package dev.CodeWizz.Luqara.items;

import java.lang.reflect.InvocationTargetException;

import dev.CodeWizz.Luqara.HUD;
import dev.CodeWizz.Luqara.world.tiles.Tile;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.gfx.Image;

public abstract class ItemStack {

	protected String name, lore, group;
	protected float x, y;
	protected int size;
	protected int maxSize;
	protected Image icon; 
	protected Image idleTexture;
	protected Image runTexture;
	protected boolean fromBlock;
	protected int width = 0;
	protected int height = 0;
	protected int actionAnimCooldown;
	protected float damage;
	protected String serialData;
	protected Type type;
	
	public ItemStack(int size) {
		this.size = size;
	}
	
	public void click(GameContainer gc, int x, int y, boolean wasLeftClick, ItemStack item) {
		
	}
	
	public void declick(GameContainer gc, int x, int y, boolean wasLeftClick, ItemStack item) {
		
	}
	
	public void rightClick(float f, float g, Tile tile) {}
	public void tick() {}
	
	@Override
	public ItemStack clone() {
		try {
			ItemStack b = getClass().getDeclaredConstructor().newInstance();
			return b;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isUnstackable() {
		return this.maxSize == 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLore() {
		return lore;
	}

	public void setLore(String lore) {
		this.lore = lore;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		if(size > 0) {
			this.size = size;
		} else {
			HUD.chat.sendMessage("&cItemStack.size went to 0");
			this.size = size;
		}
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}

	public Image getIdleTexture() {
		return idleTexture;
	}

	public void setIdleTexture(Image idleTexture) {
		this.idleTexture = idleTexture;
	}

	public Image getRunTexture() {
		return runTexture;
	}

	public void setRunTexture(Image runTexture) {
		this.runTexture = runTexture;
	}

	public boolean isFromBlock() {
		return fromBlock;
	}

	public void setFromBlock(boolean fromBlock) {
		this.fromBlock = fromBlock;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getActionAnimCooldown() {
		return actionAnimCooldown;
	}

	public void setActionAnimCooldown(int actionAnimCooldown) {
		this.actionAnimCooldown = actionAnimCooldown;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public String getSerialData() {
		return serialData;
	}

	public void setSerialData(String serialData) {
		this.serialData = serialData;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
