package okushama.engine.net;

public interface INetworkHook {
	
	public void onPacket(ManagerBase handler, String packet);
	public void onLogic(ManagerBase handler);

}
