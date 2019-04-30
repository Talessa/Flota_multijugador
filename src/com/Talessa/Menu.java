package com.Talessa;

import java.util.Scanner;

public class Menu {
    Scanner scanner= new Scanner(System.in);
    int resposta;


    public int Inici(){
        resposta=0;
        System.out.println("Que desea hacer?");
        System.out.println("1-Jugar partida");
        System.out.println("2-Salir");
        resposta=scanner.nextInt();
        scanner.nextLine();

        return resposta;
    }

    public int c_s(){
        resposta=0;
        System.out.println("Que desea Hacer?");
        System.out.println("1-Crear Partida");
        System.out.println("2-Unirse a una partida");
        resposta=scanner.nextInt();
        scanner.nextLine();
        return resposta;
    }
}
