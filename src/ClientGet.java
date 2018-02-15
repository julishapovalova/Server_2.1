
import java.io.DataInputStream;
import java.io.IOException;

public class ClientGet extends Thread {
	DataInputStream in = null;

	ClientGet(DataInputStream in) {
		this.in = in;
		this.start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (in.available() > 0) {
					System.out.println(in.readUTF());
				}
				Thread.sleep(50);
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}
