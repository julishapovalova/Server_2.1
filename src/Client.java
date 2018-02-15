
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket cc = new Socket("localhost", 4444); // подключение к сокту
		DataOutputStream out = new DataOutputStream(cc.getOutputStream()); // выходной поток
		ClientGet cg = new ClientGet(new DataInputStream(cc.getInputStream()));

		Scanner inn = new Scanner(System.in);
		while (true) {
			String tmp = inn.nextLine();
			out.writeUTF(tmp); // выдать
			out.flush();
			System.out.println("aecho " + tmp);
		}
	}
}
