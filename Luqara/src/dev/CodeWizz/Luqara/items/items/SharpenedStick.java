package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.util.Textures;

public class SharpenedStick extends Tool {

	private int counter = 0;
	
	public SharpenedStick(int size) {
		super(size);

		this.maxSize = 1;
		this.size = 1;
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
	public void tick() {
		if(attacking) {
			if(counter < 4*6)
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
				if(wasLeftClick) {
					attack(x, y);
				}
			}
		} 
	}
}
