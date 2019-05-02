package com.Talessa.Client_Servidor;

import com.Talessa.Joc.Jugada;
import com.Talessa.Joc.Tablero;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.*;


public class DatagramSocketClient {

    InetAddress ipservidor;
    int puertoservior;
    DatagramSocket socket;
    boolean fin=false;
    Tablero tablero= new Tablero();
    Tablero tableroj2 = new Tablero();
    boolean primero = true;
    int jugador=0;
    Jugada jugada=null;

    public void init(String host,int port) throws UnknownHostException, SocketException {
        ipservidor=InetAddress.getByName(host);
        puertoservior=port;
        socket = new DatagramSocket();
        tablero.iniciarTablero();
        tablero.colBarcos();
        System.out.println("Tu tablero");
        tablero.pintart();
        tableroj2.iniciarTablero();
        jugada= new Jugada(tablero.getBarcosMap());
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
            if (primero){
                datosS=getFristDataToRequest(packet.getData(),packet.getLength());
            }else {
                datosS = getDataToRequest(packet.getData(), packet.getLength());
            }
        }
    }

    //primer procesado de los datos
    private byte[] getFristDataToRequest(byte[] data, int length) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            jugada = (Jugada) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //compruebo que el estado del servidor es diferente de 4
        if (jugada.getEstado() != 4) {
            //compruevo el nº de jugador que me a asignado el servidor y me lo asigno.
            jugador = jugada.getJugador();
            System.out.println("Jugador asignado somos jugador "+jugador);
            System.out.println("Esperando a otro jugador");
            // pongo en false primero para no volver a ejecutar este codigo
            primero = false;
        } else {
            System.out.println("Servidor lleno intentelo mas tarde");
            System.exit(0);
        }
        //proceso la jugada y se la envio al servidor
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(jugada);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] respuesta = os.toByteArray();
        return respuesta;
    }
    //procesado de los datos recividos
    private byte[] getDataToRequest(byte[] data, int length) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            jugada=(Jugada) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //compruebo que codigo de estado me a enviado el servidor.
        switch (jugada.getEstado()){
            
            case 0:
                esperar();
                break;
            
            case 3:
                ganar();
                break;
            
            default:
                procesarjugada();    
        }


        //proceso la jugada y se la envio al servidor
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(jugada);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] respuesta = os.toByteArray();
        return respuesta;
    }

    private void procesarjugada() {
        //proceso la jugada
        tableroj2.procesarJugada(jugada.getJugada(),jugada.getResultado());
        //muestro el resultado de la jugada
        switch (jugada.getResultado()){
            case 1:
                System.out.println("Has fallado");
                break;
            case 2:
                System.out.println("Tocado y hundido");
                break;
        }
        //pinto el tabero actualizado
        tableroj2.pintar();
        //solicito la jugada
        jugada.setJugada(tableroj2.pedirJugada());
        //informo al usuario a que espere respuesta.
        System.out.println("Espere a su turno.");
    }

    private void ganar() {
        tableroj2.procesarJugada(jugada.getJugada(),jugada.getResultado());
        tableroj2.pintar();
        System.out.println("Todos los barcos hundidos has ganado");
        System.out.println("Has ganado");
        fin=true;
    }

    private void esperar() {
        Integer[] jugDefecto = {99,99};
        jugada.setJugada(jugDefecto);
    }

    // primer envio de datos.
    private byte[] getFirstRequest() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(jugada);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] respuesta = os.toByteArray();
        return respuesta;
    }
}
