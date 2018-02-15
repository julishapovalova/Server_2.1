
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerIO extends Thread {
	ArrayList<Socket> soc = null;

	public ServerIO(ArrayList<Socket> soc) {
		this.soc = soc;
		this.start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				for (int i = 0; i < soc.size(); i++) {
					DataInputStream in = new DataInputStream(soc.get(i).getInputStream());
					if (in.available() > 0) {
						String str = in.readUTF();

						for (int j = 0; j < soc.size(); j++) {
							DataOutputStream out = new DataOutputStream(soc.get(j).getOutputStream());
							out.writeUTF(str);
							out.flush();
							System.out.println("fromclient => " + str);
						}
					}
				}
				Thread.sleep(50);
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}
