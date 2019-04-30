package com.Talessa.Client_Servidor;

import com.Talessa.Joc.Tablero;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class DatagramSocketClient {

    InetAddress ipservidor;
    int puertoservior;
    DatagramSocket socket;
    boolean fin=false;
    Tablero tablero= new Tablero(11,5);
    Tablero tableroj2 = new Tablero(11,5);

    public void init(String host,int port) throws UnknownHostException, SocketException {
        ipservidor=InetAddress.getByName(host);
        puertoservior=port;
        socket = new DatagramSocket();
        tablero.iniciarTablero();
        tablero.colBarcos();
        tableroj2.iniciarTablero();
    }

    public void runClient() throws IOException {
        byte[] datosE = new byte[12];
        byte[] datosS;

        datosS= getFirstRequest();

        while (!fin){
            DatagramPacket packet = new DatagramPacket(datosS,datosS.length,ipservidor,puertoservior);
            socket.send(packet);
            packet=new DatagramPacket(datosE,12);
            socket.receive(packet);
            datosS=getDataToRequest(packet.getData(),packet.getLength());
        }
    }

    private byte[] getDataToRequest(byte[] data, int length) {
        int num = ByteBuffer.wrap(data).getInt();
        // comprobar si a finalizado el juego
        //  si finaliza mostrar mensaje y acabar el programa
        // si no fin procesar coordenadas enviada
        // comprobar coordenadas
        // comprobar si se pierde
        // enviar respuesta


        return data;
    }

    private byte[] getFirstRequest() {
        byte[] bytes= new byte[12];
        // mostrar tablero enemigo
        // preguntar coordenadas a lanzar bomba
        // procesar coordenadas
        // montar paquete
        return bytes;
    }
}
