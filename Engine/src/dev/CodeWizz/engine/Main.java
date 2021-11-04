package dev.CodeWizz.engine;

public class Main extends AbstractGame {

	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GameContainer gc) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new Main());
		gc.start();
	}
}
