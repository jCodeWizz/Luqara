package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.util.Textures;

public class SmallRock extends ItemStack {
	
	public SmallRock(int size) {
		super(size);
		
		this.size = size;
		this.maxSize = 4;
		
		this.type = Type.SmallRock;
		this.icon = Textures.get("smallrock");
		this.idleTexture = Textures.get("air");
		this.runTexture = Textures.get("air");
		
		this.name = "Small Rock";
		this.group = "Item";
		this.lore = "";
		
		this.serialData = "008";
	}

}
