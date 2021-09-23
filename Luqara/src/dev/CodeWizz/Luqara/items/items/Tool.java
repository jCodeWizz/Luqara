package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.engine.gfx.Animation;

public abstract class Tool extends ItemStack{

	protected boolean attacking;
	protected int durability;
	protected Animation attackAnim;
	
	public Tool(int size) {
		super(size);
		
	}
	
	public void tick() {
		attackAnim.tick();
	
	}
	
	public void click(int x, int y, boolean wasLeftClick, ItemStack item) {
		
	}
	
	public void declick(int x, int y, boolean wasLeftClick, ItemStack item) {
		
	}

	public void attack(int x, int y) {
		attacking = true;
		attackAnim.reset();
		
	}
	
	public Animation getAttackAnim() {
		return attackAnim;
	}

	public void setAttackAnim(Animation attackAnim) {
		this.attackAnim = attackAnim;
	}

	

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
