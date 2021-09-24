package dev.CodeWizz.Luqara;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.hud.Chat;

public class HUD {

	public static Chat chat;
	
	public HUD(GameContainer gc) {
		chat = new Chat(gc, 0, gc.getHeight());
		
		gc.gethMan().addComponent(chat);
	}
}
