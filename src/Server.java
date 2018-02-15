
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	static ArrayList<Socket> soc = new ArrayList<Socket>();

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(4444);
		ServerIO sio = new ServerIO(soc);
		while (true) {
			soc.add(ss.accept());
		}
	}

}
