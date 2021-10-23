package dev.CodeWizz.Luqara.items.items;

import dev.CodeWizz.Luqara.items.ItemStack;
import dev.CodeWizz.Luqara.items.Type;
import dev.CodeWizz.engine.util.Textures;

public class TwigBasket extends ItemStack {

	private int berryAmount = 0;
	
	
	public TwigBasket(int size) {
		super(size);
		
		this.size = size;
		this.maxSize = 1;
		
		this.type = Type.TwigBasket;
		this.damage = 1;
		this.icon = Textures.get("twigbasket");
		this.idleTexture = Textures.get("air");
		this.runTexture = Textures.get("air");
		
		this.name = "Twig Basket";
		this.group = "Food";
		this.lore = "For storing berries";
		this.serialData = "010";
	}


	public int getBerryAmount() {
		return berryAmount;
	}


	public void setBerryAmount(int berryAmount) {
		this.berryAmount = berryAmount;
	}
	
	@Override
	public String getLore() {
		return "Berries: " + berryAmount + "/10";
	}
}
