package dev.CodeWizz.Luqara.util;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.hud.Button;
import dev.CodeWizz.engine.hud.Chat;

public class HUD {

	public static Chat chat;
	public static ActionRenderer ar;
	public static Button button;
	
	
	public HUD(GameContainer gc) {
		chat = new Chat(gc, 0, gc.getHeight());
		ar = new ActionRenderer();
		button = new Button(0, 0, 100, 50, "LLL") {
			@Override
			public void click(GameContainer gc) {

			}
		};
		
		gc.gethMan().addComponent(ar);
		gc.gethMan().addComponent(chat);
		gc.gethMan().addComponent(button);
	}
}
