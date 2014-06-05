package okushama.engine.core;

import okushama.engine.util.Registry;


public abstract class SimpleGame {
	
	public final Engine engine;
	private final Input gameInput =  new GameInput(this);
	private final RenderTicker renderTicker = new RenderTicker(this);
	private final LogicTicker logicTicker = new LogicTicker(this);
	
	public String gameTitle;
	public int windowWidth, windowHeight, displayScale, displayWidth, displayHeight;
	
	public SimpleGame(String title, int width, int height, int scaleFactor, String icon){
		gameTitle = title;
		windowWidth = width;
		windowHeight = height;
		displayScale = scaleFactor;
		displayWidth = windowWidth / scaleFactor;
		displayHeight = windowHeight / scaleFactor;
		engine = new Engine(title, windowWidth, windowHeight, scaleFactor, icon);
		Registry.registerInput(this, gameInput);
		Registry.registerTicker(this, logicTicker);
		Registry.registerTicker(this, renderTicker);
		init();
		engine.run();
	}
	
	
	
	public void setDisplay(int w, int h, int scale){
		windowWidth = w;
		windowHeight = h;
		displayScale = scale;
		displayWidth = windowWidth / displayScale;
		displayHeight = windowHeight / displayScale;
		engine.setDisplayMode(windowWidth, windowHeight, displayScale);
	}
	
	public abstract void init();
	public abstract void onRenderTick(float pt);
	public abstract void onLogicTick(float pt);
	public abstract void onMouseClick(int btn);
	public abstract void onKeyPress(int key);
	
	private static class GameInput extends Input{

		public SimpleGame game;
		public GameInput(SimpleGame g){
			game = g;
			
		}
		
		@Override
		public void onMouseClick(int b) {
			game.onMouseClick(b);
		}

		@Override
		public void onMouseScroll(int dir) {
			
		}

		@Override
		public void onKeyPress(int k) {
			game.onKeyPress(k);
		}
	
	}


}
