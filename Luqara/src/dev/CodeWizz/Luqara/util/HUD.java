package dev.CodeWizz.Luqara.util;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.hud.Button;
import dev.CodeWizz.engine.hud.Chat;

public class HUD {

	public static Chat chat;
	public static ActionRenderer ar;
	private GameContainer gc;
	
	
	public HUD(GameContainer gc) {
		chat = new Chat(gc, 0, gc.getHeight());
		ar = new ActionRenderer();
		
		this.gc = gc;
		
		gc.gethMan().addComponent(ar);
		gc.gethMan().addComponent(chat);
		
		addButton(new Button(0, 0, 100, 50, "Bro nice ****") {
			@Override
			public void click(GameContainer gc) {

			}
		});
	}
	
	public void addButton(Button button) {
		gc.gethMan().addComponent(button);
	}
	
	public void removeButton(Button button) {
		gc.gethMan().removeComponent(button);
	}
}
