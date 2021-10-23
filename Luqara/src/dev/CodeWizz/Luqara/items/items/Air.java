package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.util.Textures;

public class Air extends ItemStack{

	public Air(int size) {
		super(size);
		
		this.maxSize = 1;
		this.size = size;
		this.type = Type.Air;
	
		this.idleTexture = Textures.get("air");
		this.runTexture = Textures.get("air");
		
		this.serialData = "000";
	}
}
