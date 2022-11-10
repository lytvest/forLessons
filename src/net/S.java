package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {

    public static final int SERVER_PORT = 50001;

    public static void main (String[] args){

        try {
            ServerSocket server = new ServerSocket(SERVER_PORT);
            Socket clientConn = server.accept();
            InputStream is = clientConn.getInputStream();
            new Scanner(new BufferedReader(new InputStreamReader(is)));

            DataOutputStream serverOutput = new DataOutputStream(clientConn.getOutputStream());
            serverOutput.writeBytes("Java revisited\n");
            clientConn.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
