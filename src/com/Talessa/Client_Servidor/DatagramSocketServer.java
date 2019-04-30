package com.Talessa.Client_Servidor;

import com.Talessa.Joc.Jugada;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class DatagramSocketServer {

    DatagramSocket socket;
    boolean fin = false;
    int jugador = 0;
    int[] jugadores = {1,2};
    Map<Integer,Integer[]> jugador1 = new HashMap<>();
    Map<Integer,Integer[]> jugador2 = new HashMap<>();
    int[] barcosT = {0,0};
    Jugada jugada=null;



    public void init (int puerto) throws SocketException {
        socket= new DatagramSocket(puerto);
    }

    public void runServer() throws IOException {
        byte[] datosE = new byte[1024];
        byte[] datosS;
        InetAddress ipcliente;
        int puertocliente;

        while (!fin){
            DatagramPacket packet = new DatagramPacket(datosE,12);
            socket.receive(packet);

            datosS=processData(packet.getData(),packet.getLength());

            ipcliente=packet.getAddress();
            puertocliente=packet.getPort();

            packet = new DatagramPacket(datosS,datosS.length,ipcliente,puertocliente);
            socket.send(packet);
        }
        socket.close();
    }

    private byte[] processData(byte[] data, int length) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            jugada=(Jugada) objectInputStream.readObject();
            if (jugada.getJugador() == 0){
                nuevoJugador();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return data;
    }

    private void nuevoJugador() {
        if (jugador <=2){
            jugador++;
            if (jugador==1){
                jugador1=jugada.getBarcos();
                jugada.setEstado(0);
                jugada.setResultado(0);
            }
            if (jugador==2){
                jugador2=jugada.getBarcos();
                jugada.setEstado(0);
                jugada.setResultado(0);
            }
        }
    }
}
