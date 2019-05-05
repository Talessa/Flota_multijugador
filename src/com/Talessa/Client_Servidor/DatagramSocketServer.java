package com.Talessa.Client_Servidor;

import com.Talessa.Joc.Jugada;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class DatagramSocketServer {

    DatagramSocket socket;
    boolean fin = false; // true si gana algun jugdor
    int jugadores = 0; //controla la cantidad de jugadores posibles
    int jugador = 1; // controla el turno del jugador que le toca
    Map<String, Integer> jugador1 = new HashMap<>(); // map con las posiciones de los barcos y su estado del j1
    Map<String, Integer> jugador2 = new HashMap<>(); // map con las posiciones de los barcos y su estado del j2
    int[] barcosT = {0, 0}; // cantidad de barcos hundidos del j1 y j2
    Jugada jugada = null;
    Integer[] jugadanula = {99, 99};


    public void init(int puerto) throws SocketException {
        socket = new DatagramSocket(puerto);
    }

    public void runServer() throws IOException {
        byte[] datosE = new byte[1024];
        byte[] datosS;
        InetAddress ipcliente;
        int puertocliente;

        while (!fin) {
            DatagramPacket packet = new DatagramPacket(datosE, 1024);
            socket.receive(packet);

            datosS = processData(packet.getData(), packet.getLength());

            ipcliente = packet.getAddress();
            puertocliente = packet.getPort();

            packet = new DatagramPacket(datosS, datosS.length, ipcliente, puertocliente);
            socket.send(packet);
        }
        socket.close();
    }

    private byte[] processData(byte[] data, int length) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            jugada = (Jugada) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (jugadores < 2) {
            if (jugada.getJugador() == 0) {
                nuevoJugador();
            } else {
                estadoesperar();
            }
        } else {
            if (jugada.getJugador() == jugador) {
                procesarjugada();
            } else if (jugada.getJugador() == 0) {
                servidorlleno();
            } else {
                estadoesperarturno();
            }
        }

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

    private void servidorlleno() {
        jugada.setEstado(4);
    }

    private void estadoesperarturno() {
        jugada.setEstado(jugador);
        jugada.setResultado(0);
    }

    private void procesarjugada() {
        Integer[] jugadanula = jugada.getJugada();
        //crea una comprobacion si jugada es 99,99 no se procesa ya que es la jugada nula
        if (jugadanula[0] == 99 && jugadanula[1] == 99) {
            jugada.setResultado(0);
            jugada.setEstado(jugador);
        } else {
            String key = "" + jugadanula[0] + jugadanula[1];
            if (jugada.getJugador() == 1) {
                for (Map.Entry<String, Integer> entry : jugador2.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(key)) {
                        if (entry.getValue() == 0) {
                            entry.setValue(1);
                            jugada.setResultado(2);
                            barcosT[0]++;
                        } else {
                            jugada.setResultado(2);
                        }
                    }
                }
                if (barcosT[0] == jugada.getTotalbarcos()) {
                    jugada.setEstado(3);
                    jugada.setResultado(3);
                    fin = true;
                } else {
                    jugada.setResultado(1);
                }
                jugador = 2;
                jugada.setEstado(jugador);
            } else if (jugada.getJugador() == 2) {
                for (Map.Entry<String, Integer> entry : jugador1.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(key)) {
                        if (entry.getValue() == 0) {
                            entry.setValue(1);
                            jugada.setResultado(2);
                            barcosT[1]++;

                        } else {
                            jugada.setResultado(2);
                        }
                    }
                }
                if (barcosT[1] == jugada.getTotalbarcos()) {
                    jugada.setEstado(3);
                    jugada.setResultado(4);
                    fin = true;
                } else {
                    jugada.setResultado(1);
                }
                jugador = 1;
                jugada.setEstado(jugador);
            }
        }
    }

    private void estadoesperar() {
        jugada.setEstado(0);
        jugada.setResultado(0);
    }

    private void nuevoJugador() {
        if (jugadores < 2) {
            jugadores++;
            if (jugadores == 1) {
                jugador2 = jugada.getBarcos();
                jugada.setEstado(0);
                jugada.setResultado(0);
                jugada.setJugador(1);
            }
            if (jugadores == 2) {
                jugador1 = jugada.getBarcos();
                jugada.setEstado(0);
                jugada.setResultado(0);
                jugada.setJugador(2);
            }
        } else {
            jugada.setEstado(4);
        }
    }
}
