package dev.CodeWizz.Luqara.input;

import dev.CodeWizz.Luqara.HUD;
import dev.CodeWizz.Luqara.Luqara;
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
			
			if(arg.equalsIgnoreCase("debug")) {
				GameContainer._debug = true;
				HUD.chat.sendMessage("Entered debug mode!");
				return true;
			} else if(arg.equalsIgnoreCase("checksizes")) {
				Luqara.inst.getWorld().sendInfoInChat(gc);
				return true;
			}
			
			HUD.chat.sendMessage("&cWrong command usage!");
			return true;
		}
		
		
		return false;
	}
}
