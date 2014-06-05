package okushama.engine.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class Server implements Runnable {


	public ServerSocket socket;
	public ArrayList<ManagerServer> clients = new ArrayList<ManagerServer>();
	public int connected = 0, port = 0;
	public INetworkHook handlerHook;

	public Server(int p, INetworkHook hh) {
		port = p;
		handlerHook = hh;
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println("Awaiting client connection!");
		while (true) {
			try {
				lookForNewClients();
				Thread.sleep(1000);
			} catch (Exception e) {
				 e.printStackTrace();
			}
		}
	}

	public void lookForNewClients() {
		try {
			// System.out.println("LOOKING FOR PPLZ!!1");
			socket.setSoTimeout(10);
			Socket clientSocket = socket.accept();
			ManagerServer newThread = new ManagerServer(this, clientSocket, handlerHook);
			clients.add(newThread);
			System.out.println("Client Connected!"
					+ clientSocket.getInetAddress().getHostAddress());
			connected++;
			Thread.sleep(1000);
			System.out.println("Awaiting client connection!");

		} catch (Exception e) {
			if (!(e instanceof SocketTimeoutException))
				e.printStackTrace();
		}
	}

}
