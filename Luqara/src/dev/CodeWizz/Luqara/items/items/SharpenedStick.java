package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.util.Textures;

public class SharpenedStick extends Tool {

	public SharpenedStick(int size) {
		super(size);

		this.maxSize = 1;
		this.size = size;
		this.durability = 1;
		
		this.type = Type.SharpenedStick;
		this.damage = 5;
		this.attackAnim = new Animation(4, Textures.get("sharpenedstickAttack1"), Textures.get("sharpenedstickAttack2"), Textures.get("sharpenedstickAttack3"), Textures.get("sharpenedstickAttack4"), Textures.get("sharpenedstickAttack5"));
		this.icon = Textures.get("sharpenedstickIcon");
		this.idleTexture = Textures.get("sharpenedstickIdle");
		this.runTexture = Textures.get("sharpenedstickRun");
		
		this.name = "Sharpened Stick";
		this.group = "Weapon";
		this.lore = "First item to be ever added into the game!";
		
		this.serialData = "0006";
	}
	
	@Override
	public void click(GameContainer gc, int x, int y, boolean wasLeftClick, ItemStack item) {
		if(wasLeftClick && !gc.getPlayer().isHitting())
			attack(gc, x, y);
	}
}
