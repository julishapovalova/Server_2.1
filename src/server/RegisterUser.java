package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterUser {
    ArrayList<SocketFromClient> soc = null;

    public RegisterUser(ArrayList<SocketFromClient> soc) {
        this.soc = soc;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < soc.size(); i++) {
                    DataInputStream in = new DataInputStream(soc.get(i).socket.getInputStream());
                    if (in.available() > 0) {
                        String str = in.readUTF();
                        if (str.contains("nick")) {
                            setNickName(str, soc.get(i).name);
                        }
                        for(int j = 0; j < soc.size(); j++) {
                            DataOutputStream out = new DataOutputStream(soc.get(j).socket.getOutputStream());
                            if (str.equals("quit")) {
                                System.out.println("User " + soc.get(j).name + " quit");
                                in.close();
                                soc.get(j).socket.close();
                                soc.remove(j);
                            }
                            out.writeUTF(soc.get(j).name + ":" + str);
                            out.flush();
                            System.out.println("Message from client => " + soc.get(j).name + ":" + str);
                        }
                    }
                }
                Thread.sleep(50);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }


    void setNickName(String str, String nickname) {
        nickname = str.substring(4);

    }
}
