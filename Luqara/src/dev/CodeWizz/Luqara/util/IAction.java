package dev.CodeWizz.Luqara.util;

import dev.CodeWizz.engine.GameContainer;

public interface IAction {

	public void startAction(GameContainer gc);
	public void endAction(GameContainer gc);
	public void stopAction(GameContainer gc);
}
