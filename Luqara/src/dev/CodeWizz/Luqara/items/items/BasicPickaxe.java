package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.util.Textures;

public class BasicPickaxe extends Tool {

	public BasicPickaxe(int size) {
		super(size);

		this.maxSize = 1;
		this.size = size;
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
}
