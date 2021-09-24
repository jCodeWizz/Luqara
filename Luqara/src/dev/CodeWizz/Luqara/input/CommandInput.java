package dev.CodeWizz.Luqara.input;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.hud.IChatListener;

public class CommandInput implements IChatListener {

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
			}
		
		}
		
		
		return false;
	}
}
