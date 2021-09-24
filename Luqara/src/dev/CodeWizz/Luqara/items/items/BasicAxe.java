package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.util.Textures;

public class BasicAxe extends Tool {

	private int counter = 0;
	
	public BasicAxe(int size) {
		super(size);

		this.maxSize = 1;
		this.size = 1;
		this.durability = 1;
		
		this.type = Type.BasicAxe;
		this.damage = 2;
		this.attackAnim = new Animation(4, Textures.get("basicaxeAttack1"), Textures.get("basicaxeAttack2"), Textures.get("basicaxeAttack3"), Textures.get("basicaxeAttack4"), Textures.get("basicaxeAttack5"));
		this.icon = Textures.get("basicaxeIcon");
		this.idleTexture = Textures.get("basicaxeIdle");
		this.runTexture = Textures.get("basicaxeIdle");
		
		this.name = "Basic Axe";
		this.group = "Tool";
		this.lore = "For cutting trees?";
		
		this.actionAnimCooldown = 20;
		this.serialData = "001";
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
	
}
