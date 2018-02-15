package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Enter nickname:");
		Scanner inn = new Scanner(System.in);
		String nickname=inn.next();
		Socket cc = new Socket("localhost", 4444); // ����������� � �����
		DataOutputStream out = new DataOutputStream(cc.getOutputStream()); // �������� �����
		out.writeUTF("nick"+nickname);
		ClientGet cg = new ClientGet(new DataInputStream(cc.getInputStream()));
		
		while (true) {
			String tmp = inn.nextLine();
			out.writeUTF(nickname + ":" + tmp); // ������
			out.flush();
		}
	}
}
