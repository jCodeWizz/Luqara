package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.util.Textures;

public class SharpRock extends Tool {

	public SharpRock(int size) {
		super(size);
		
		this.size = size;
		this.maxSize = 1;
		this.durability = 10;
		
		this.type = Type.SharpRock;
		this.damage = 2;
		//this.attackAnim = new Animation(4, Textures.sharpenedStickAttack);
		this.icon = Textures.get("sharprockIcon");
		this.idleTexture = Textures.get("sharprockIdle");
		this.runTexture = Textures.get("sharprockRun");
		
		this.name = "Sharp Rock";
		this.group = "Tool";
		this.lore = "A rock, but sharp!";
		this.serialData = "007";
	}
	
}
