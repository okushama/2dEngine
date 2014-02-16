package okushama.engine.core;

public interface ITicker{
	
	public void onLogicTick(float partialTick);
	public void onRenderTick(float partialTick);

}
