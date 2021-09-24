package dev.CodeWizz.Luqara.input;

import java.awt.event.KeyEvent;

import dev.CodeWizz.Luqara.HUD;
import dev.CodeWizz.Luqara.Player;
import dev.CodeWizz.engine.GameContainer;

public class KeyInput {

	
	public void update(GameContainer gc) {
		Player p = gc.getPlayer();
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_E)) {
			if(p.getInv().isOpen()) {
				p.getInv().close();
			} else {
				p.getInv().open();
			}
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_F)) {
			if(!p.getInv().isOpen()) {
				p.startAction();
			}
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_T)) {
			if(!p.getInv().isOpen()) 
				HUD.chat.setOpen(true);
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_SHIFT) && gc.getInput().isKey(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_ESCAPE)) {
			if(!p.getInv().isOpen()) {
				HUD.chat.setOpen(false);
			} else {
				p.getInv().close();
			}
		}
	}
}
