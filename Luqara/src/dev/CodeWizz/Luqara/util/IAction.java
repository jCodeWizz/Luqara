package dev.CodeWizz.Luqara.util;

import dev.CodeWizz.engine.GameContainer;

public interface IAction {

	public int getActionTime();
	public boolean startAction(GameContainer gc);
	public void endAction(GameContainer gc);
	public void stopAction(GameContainer gc);
	public int offsetX();
	public int offsetY();
	public String getActionString();
}
