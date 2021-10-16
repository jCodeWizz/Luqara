package dev.CodeWizz.Luqara.util;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Font;
import dev.CodeWizz.engine.hud.IHudComponent;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.util.WMath;

public class ActionRenderer implements IHudComponent {

	private GameObject target;
	
	
	@Override
	public void tick(GameContainer gc) {
		float distance = Float.MAX_VALUE;
		boolean wasSet = false;
		
		for(GameObject object : gc.handler.object) {
			if (object instanceof IAction) {
				float d = WMath.distance((int) gc.getPlayer().getX(), (int) gc.getPlayer().getY(), (int) object.getX() + 16, (int) object.getY());
				if (d < distance && d < 48) {
					distance = d;
					target = object;
					wasSet = true;
				}
			}
		}
		
		if(!wasSet)
			target = null;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		if(target != null && !gc.getPlayer().isDoingAction()) {
			r.setFont(Font.STANDARD);
			r.drawText(((IAction) target).getActionString(), (int)target.getX() + ((IAction) target).offsetX(), (int)target.getY() + ((IAction) target).offsetY(), 0xffffffff, true);
			r.setFont(Font.DETAILED);
		}
	}

	@Override
	public Rectangle getBounds(GameContainer gc) {
		return new Rectangle(-100, -100, 1, 1);
	}

	@Override
	public void click(GameContainer gc) {
		
	}
}
