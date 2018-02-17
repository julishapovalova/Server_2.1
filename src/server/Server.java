package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	static ArrayList<SocketFromClient> soc = new ArrayList<>();
	static ArrayList<String> name = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(4444);
		ServerIO sio = new ServerIO(soc);
		int i=0;
		while (true) {
			SocketFromClient s1=new SocketFromClient(ss.accept());
			soc.add(i,s1);
			i++;
		}
	}

}
