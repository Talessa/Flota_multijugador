package com.Talessa;

import com.Talessa.Client_Servidor.DatagramSocketServer;
import java.io.IOException;
import java.net.SocketException;

public class MainServer {

    public static void main(String[] args) {
        DatagramSocketServer server = new DatagramSocketServer();

        try {
            server.init(5555);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
