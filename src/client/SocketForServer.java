package client;

import java.net.Socket;

public class SocketForServer {
    Socket ss;
    String name;

    public SocketForServer(Socket ss, String name) {
        this.ss = ss;
        this.name = name;
    }
}
