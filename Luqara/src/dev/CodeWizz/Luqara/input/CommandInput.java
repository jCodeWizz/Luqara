package dev.CodeWizz.Luqara.input;

import dev.CodeWizz.Luqara.Luqara;
import dev.CodeWizz.Luqara.Player;
import dev.CodeWizz.Luqara.util.HUD;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.hud.IChatListener;

public class CommandInput implements IChatListener {

	private GameContainer gc;
	
	public CommandInput(GameContainer gc) {
		this.gc = gc;
	}
	
	@Override
	public boolean onChatMessage(String text) {
		
		if(text.charAt(0) == '/') {
			String arg = "";
			int index = text.indexOf(' ');
			if(index > -1) {
				arg = text.substring(1, index);
			} else {
				arg = text.substring(1, text.length());
			}
			
			if(arg.equalsIgnoreCase("checksizes")) {
				Luqara.inst.getWorld().sendInfoInChat(gc);
				return true;
			}
			
			if(arg.equalsIgnoreCase("chunk")) {
				if(HUD._chunks)
					HUD._chunks = false;
				else
					HUD._chunks = true;
				return true;
			}
			
			if(arg.equalsIgnoreCase("hitbox")) {
				if(HUD._hitboxes)
					HUD._hitboxes = false;
				else
					HUD._hitboxes = true;
				return true;
			}
			
			if(arg.equalsIgnoreCase("fly")) {
				if(Player._flying)
					Player._flying = false;
				else
					Player._flying = true;
				return true;
			}
			
			
			
			HUD.chat.sendMessage("&cWrong command usage!");
			return true;
		}
		
		
		return false;
	}
}
