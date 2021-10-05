package dev.CodeWizz.Luqara.util;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.hud.Chat;

public class HUD {

	public static Chat chat;
	public static ActionRenderer ar;
	
	public HUD(GameContainer gc) {
		chat = new Chat(gc, 0, gc.getHeight());
		ar = new ActionRenderer();
		
		gc.gethMan().addComponent(ar);
		gc.gethMan().addComponent(chat);
	}
}
