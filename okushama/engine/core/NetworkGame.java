package okushama.engine.core;

import java.net.Socket;

import okushama.engine.net.ManagerClient;
import okushama.engine.net.ManagerBase;
import okushama.engine.net.INetworkHook;

public abstract class NetworkGame extends SimpleGame {

	public ManagerClient manager;

	public NetworkGame(String title, int width, int height, int scaleFactor, String icon) {
		super(title, width, height, scaleFactor, icon);

	}

	public void connectToServer(String host, int port, INetworkHook hook) {
		try {
			manager = new ManagerClient(this, new Socket(host, port), hook);
			manager.sendPacket("game=" + gameTitle);
		} catch (Exception e) {
			System.out.println("Could not connect to server!");
		}
	}


}
