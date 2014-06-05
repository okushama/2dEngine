package okushama.engine.net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import okushama.engine.core.Engine;



public class ManagerServer extends ManagerBase{
	
	public Server server;
	public String name;
	public int id;
		
	public ManagerServer(Server s, Socket client, INetworkHook h){
		super(client, h);
		server = s;
	}
}
