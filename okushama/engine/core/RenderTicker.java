package okushama.engine.core;

public class RenderTicker implements ITicker{

		public SimpleGame theGame;
		public RenderTicker(SimpleGame game){
			theGame = game;
		}
		
		@Override
		public void onLogicTick(float partialTick) {}

		@Override
		public void onRenderTick(float partialTick) {
			theGame.onRenderTick(partialTick);
		}
		
	}