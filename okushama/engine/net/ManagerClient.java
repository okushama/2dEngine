package okushama.engine.net;

import java.net.Socket;

import okushama.engine.core.SimpleGame;


public class ManagerClient extends ManagerBase{
	

	public SimpleGame game;
	public ManagerClient(SimpleGame g, Socket s, INetworkHook h){
		super(s, h);
		game = g;
		if(socket.isConnected()){
			System.out.println("Successfully Connected! "+socket.getLocalAddress().getHostAddress());
		}else{
			System.out.println("Host not found!");
		}
	}
}
