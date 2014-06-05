package okushama.engine.net;

import java.io.IOException;
import java.net.Socket;

public class Listener implements Runnable{

	public ManagerBase handler;
	public String lastMessage;
	public boolean listen = true;
	public Listener(ManagerBase s){
		handler = s;
	}
	
	public void start(){
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run(){
		while(listen){
			try{
				try{
				if((lastMessage = handler.in.readLine()) != null){
					handler.onPacket(lastMessage);
				}
				}catch(Exception e){
					break;
				}
				Thread.sleep(10);
			} catch (Exception e) {
				try {
					e.printStackTrace();
					Thread.sleep(100);
				} catch (Exception e2) {
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					e2.printStackTrace();
				}
			}
		}
		System.out.println("Connection timed out!");
		
	}

}
