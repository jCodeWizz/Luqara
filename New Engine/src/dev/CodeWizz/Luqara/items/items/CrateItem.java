package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.util.Textures;

public class CrateItem extends ItemStack implements Cloneable{

	
	public CrateItem(int size) {
		super(size);
		
		this.maxSize = 2;
		this.size = 1;
		
		this.type = Type.Crate;
		
		this.icon = Textures.get("crate");
		this.idleTexture = Textures.get("air");
		this.runTexture = Textures.get("air");
		
		this.fromBlock = true;
		
		this.name = "Crate";
		this.group = "Item";
		this.lore = "";
		
		this.serialData = "004";
		
	}
}
