package com.Talessa;

import com.Talessa.Client_Servidor.DatagramSocketClient;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        iniciarcliente("localhost",5555);
//        Scanner scanner = new Scanner(System.in);
//        int respuesta =0;
//        String ipservidor;
//        int puerto;
//
//        System.out.println("Que desea hacer?");
//        System.out.println("1-Jugar partida");
//        System.out.println("2-Salir");
//        respuesta=scanner.nextInt();
//        scanner.nextLine();
//
//        switch (respuesta){
//            case 1:
//                System.out.println("Introduzca ip del servidor");
//                ipservidor=scanner.nextLine();
//                System.out.println("Introduzca puerto del servidor");
//                puerto=scanner.nextInt();
//                scanner.nextLine();
//                iniciarcliente(ipservidor,puerto);
//                break;
//            case 2: System.exit(0);
//        }
    }

    private static void iniciarcliente(String ipservidor, int puerto) {
        DatagramSocketClient cliente = new DatagramSocketClient();
        try {
            cliente.init(ipservidor,puerto);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            cliente.runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}
