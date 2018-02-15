package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerIO extends Thread {
	ArrayList<Socket> soc = null;
	ArrayList<String> nickname = new ArrayList<String>();

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
						if (str.contains("nick")) {
							nickname.add(i, str.substring(4));
							str = "User " + str.substring(4) + " connected";
						}

						for (int j = 0; j < soc.size(); j++) {
							DataOutputStream out = new DataOutputStream(soc.get(j).getOutputStream());
							if (str.equals("quit")) {
								System.out.println("User " + str+" quit");

								in.close();
								soc.get(j).close();
								soc.remove(j);
							}
							out.writeUTF(str);
							out.flush();
							System.out.println("Message from client => " + str);
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
