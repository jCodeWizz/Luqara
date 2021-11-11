package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.util.Textures;

public class BasicAxe extends Tool {

	public BasicAxe(int size) {
		super(size);

		this.maxSize = 1;
		this.size = size;
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
	public void click(GameContainer gc, int x, int y, boolean wasLeftClick, ItemStack item) {
		if(wasLeftClick && !gc.getPlayer().isHitting())
			attack(gc, x, y);
	}
	
}
