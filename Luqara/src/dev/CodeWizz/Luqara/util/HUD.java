package dev.CodeWizz.Luqara.util;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.hud.Button;
import dev.CodeWizz.engine.hud.Chat;
import dev.CodeWizz.engine.util.UIID;

public class HUD {

	public static final int ALL = 0;
	public static final int BUTTON = 1;
	
	public static Chat chat;
	public static ActionRenderer ar;
	private GameContainer gc;
	
	public static boolean _hitboxes;
	public static boolean _chunks;
	public static boolean _debug;

	
	
	public HUD(GameContainer gc) {
		chat = new Chat(gc, 0, gc.getHeight());
		ar = new ActionRenderer();
		
		this.gc = gc;
		
		gc.gethMan().addComponent(ar);
		gc.gethMan().addComponent(chat);
	}
	
	public void render(GameContainer gc, Renderer r) {
		if(_debug) {
			
		}
	}
	
	public void addButton(Button button) {
		gc.gethMan().addComponent(button);
	}
	
	public void removeButton(Button button) {
		gc.gethMan().removeComponent(button);
	}
	
	public void clear(int type) {
		if(type == HUD.ALL) {
			gc.gethMan().clear();
		} else if(type == HUD.BUTTON) {
			gc.gethMan().clear(UIID.Button);
		}
	}
}
