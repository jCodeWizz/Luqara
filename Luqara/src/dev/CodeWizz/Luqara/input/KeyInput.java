package dev.CodeWizz.Luqara.input;

import java.awt.event.KeyEvent;

import dev.CodeWizz.Luqara.Player;
import dev.CodeWizz.Luqara.util.HUD;
import dev.CodeWizz.engine.GameContainer;

public class KeyInput {

	public void update(GameContainer gc) {
		Player p = gc.getPlayer();
		
		
		// IN GAME 
		
		
		if(Player._ENABLED) {
		
			// FLYING 
			
			if(Player._flying) {
				
				// KEY DOWN
				
				if(gc.getInput().isKeyDown(KeyEvent.VK_A)) {
					gc.getPlayer().setVelX(-10);
				}
				
				if(gc.getInput().isKeyDown(KeyEvent.VK_D)) {
					gc.getPlayer().setVelX(10);
				}
				
				if(gc.getInput().isKeyDown(KeyEvent.VK_W)) {
					gc.getPlayer().setVelY(-5);
				}
				
				if(gc.getInput().isKeyDown(KeyEvent.VK_S)) {
					gc.getPlayer().setVelY(5);
				}
				
				// KEY UP
				
				if(gc.getInput().isKeyUp(KeyEvent.VK_A)) {
					gc.getPlayer().setVelX(0);
				}
				
				if(gc.getInput().isKeyUp(KeyEvent.VK_D)) {
					gc.getPlayer().setVelX(0);
				}
				
				if(gc.getInput().isKeyUp(KeyEvent.VK_W)) {
					gc.getPlayer().setVelY(0);
				}
				
				if(gc.getInput().isKeyUp(KeyEvent.VK_S)) {
					gc.getPlayer().setVelY(0);

				}
			}
			
			// OPEN INVENTORY 
			
			if(gc.getInput().isKeyDown(KeyEvent.VK_E) && !HUD.chat.isOpen() && !gc.getPlayer().isDoingAction()) {
				if(p.getInv().isOpen()) {
					p.getInv().close();
				} else {
					p.getInv().open(gc);
				}
			}
			
			// STARTING ACTION
			
			if(gc.getInput().isKeyDown(KeyEvent.VK_F)) {
				if(!p.getInv().isOpen() && !HUD.chat.isOpen()) {
					p.startAction();
				}
			}
			
			// OPEN CHAT
			
			if(gc.getInput().isKeyDown(KeyEvent.VK_T)) {
				if(!p.getInv().isOpen() && !HUD.chat.isOpen()) {
					HUD.chat.setOpen(true);
					HUD.chat.currentText = "";
				}
			}
			
			// OPEN CHAT WITH COMMAND 
			
			if(gc.getInput().isKeyDown(KeyEvent.VK_SLASH)) {
				if(!p.getInv().isOpen() && !HUD.chat.isOpen()) {
					HUD.chat.setOpen(true);
					HUD.chat.currentText = "/";
				}
			}
			
			// CLOSE INV/CHAT 
			
			if(gc.getInput().isKey(KeyEvent.VK_ESCAPE)) {
				if(!p.getInv().isOpen()) {
					HUD.chat.setOpen(false);
				} else {
					p.getInv().close();
				}
			}
		}
		
		// NOT IN GAME LOGIC
		
		if(gc.getInput().isKey(KeyEvent.VK_SHIFT) && gc.getInput().isKey(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		
		
	}
}
