package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.util.Textures;

public class WoodLog extends ItemStack{

	public WoodLog(int size) {
		super(size);
		
		this.maxSize = 5;
		this.size = size;
		
		this.icon = Textures.get("woodlog");
		
		this.type = Type.WoodLog;
		this.idleTexture = Textures.get("air");
		this.runTexture = Textures.get("air");
		
		this.name = "Log";
		this.group = "Item";
		this.lore = "";
		
		this.serialData = "009";
	}
}
