package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.util.Textures;

public class CutWood extends ItemStack{

	public CutWood(int size) {
		super(size);
		
		this.maxSize = 10;
		this.size = 1;
		
		this.type = Type.CutWood;
		
		this.icon = Textures.get("cutwood");
		this.idleTexture = Textures.get("air");
		this.runTexture = Textures.get("air");
		
		this.name = "Cut Wood";
		this.group = "Item";
		this.lore = "";
		
		this.serialData = "005";
		
	}
}
