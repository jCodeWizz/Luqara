package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.util.Textures;

public class BasicPickaxe extends Tool {

	private int counter = 0;
	
	public BasicPickaxe(int size) {
		super(size);

		this.maxSize = 1;
		this.size = 1;
		this.durability = 1;
		
		this.type = Type.BasicAxe;
		this.damage = 2;
		this.attackAnim = new Animation(4, Textures.get("basicaxeAttack1"), Textures.get("basicaxeAttack2"), Textures.get("basicaxeAttack3"), Textures.get("basicaxeAttack4"), Textures.get("basicaxeAttack5"));
		this.icon = Textures.get("basicpickaxeIcon");
		this.idleTexture = Textures.get("basicpickaxeIdle");
		this.runTexture = Textures.get("basicpickaxeIdle");
		this.name = "Basic Pickaxe";
		this.group = "Tool";
		this.lore = "Gotta hit on them rocks";
		
		this.serialData = "002";
		
		this.actionAnimCooldown = 20;
		
		
	}
	
	@Override
	public void tick() {
		if(attacking) {
			if(counter < 4*9)
				counter++;
			else {
				counter = 0;
				attacking = false;
			}
		}
		super.tick();
	}
	
	
	@Override
	public void click(int x, int y, boolean wasLeftClick, ItemStack item) {
		if(wasLeftClick) {
			if(counter == 0) {
				attacking = true;
				attackAnim.reset();
			}
		} 
	}
}
