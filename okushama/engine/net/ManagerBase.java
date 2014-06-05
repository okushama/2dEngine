package okushama.engine.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class ManagerBase implements Runnable{

	public Socket socket;
	public PrintWriter out;
	public BufferedReader in;
	public ObjectOutputStream oout;
	public ObjectInputStream oin;
	public Listener listener;
	public boolean runLogic = true;
	public INetworkHook handlerHook;

	public ManagerBase(Socket s, INetworkHook hh) {
		try {
			handlerHook = hh;
			listener = new Listener(this);
			socket = s;
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			oout = new ObjectOutputStream(socket.getOutputStream());
			oin = new ObjectInputStream(socket.getInputStream());
			listener = new Listener(this);
			listener.start();
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run(){
		while(runLogic){
			try {
				onLogic();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void onLogic(){
		handlerHook.onLogic(this);
	}
	
	public void sendPacket(String packet){
		out.println(packet);
	}

	public void onPacket(String packet){
		handlerHook.onPacket(this, packet);
	}
}
