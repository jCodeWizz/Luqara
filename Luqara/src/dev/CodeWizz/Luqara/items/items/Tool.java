package dev.CodeWizz.Luqara.items.items;

import java.awt.Rectangle;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;

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
	
	public void attack(GameContainer gc, int x, int y) {
		gc.getPlayer().hit();
		for(GameObject object : gc.handler.object) {
			if(object.getId() == ID.Balrups && new Rectangle(x, y, 1, 1).intersects(object.getBounds())) {
				object.damage(gc, damage);
				continue;
			}
		}
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
