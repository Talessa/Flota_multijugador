package com.Talessa.Joc;

import java.util.Random;
import java.util.Scanner;

public class Tablero {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    int tablero[][];

    int[] coordenadas = new int[2];

    String barco2= "\uD83D\uDEA2";
    String agua2= "\uD83C\uDF0A";
    String agua3 = "\ud83d\udca7";

    int barco= 1;
    int barcou=3;
    int agua=2;
    int barcos;

    String bordes = "|";

    public Tablero(int tablero, int barcos) {
        this.tablero = new int[tablero][tablero];
        this.barcos = barcos;
    }

    public void  iniciarTablero(){
        //inicializamos el tablero poniendo a 0 todas las casillas
        for (int columna = 0;columna<tablero.length;columna++){
            for (int fila = 0; fila <tablero.length ; fila++) {
                tablero[columna][fila] =0;
            }
        }
    }

    public void colBarcos(){
        for (int i = 0; i <barcos ; i++) {
            int x = random.nextInt(tablero.length);
            int y = random.nextInt(tablero.length);
            if (tablero[x][y] != barco){
                tablero[x][y]=barco;
            }else {
                i--;
            }
        }
    }

    public boolean colBarcos(int x,int y){
        if (tablero[x][y]!=barco){
            tablero[x][y]=barco;
            return true;
        }else {
            return false;
        }
    }

    public int[] jugada(){
        int[] coordenadas = new int[2];
        System.out.println("Introduzca coordenada X:");
        coordenadas[0]= scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduzca coordenada Y:");
        coordenadas[1]= scanner.nextInt();
        scanner.nextLine();
        return coordenadas;
    }

    public void comprobar(int respuesta){
        if (respuesta == barco){
            barcos--;
            tablero[coordenadas[0]][coordenadas[1]] = barcou;
        }else{
            tablero[coordenadas[0]][coordenadas[1]] = agua;
        }
    }

    public void pintar(){
        System.out.print("    ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.format("%3d", i+1);
            System.out.print(bordes);
        }
        System.out.println("X");
        for (int i=0; i < tablero.length; i++){
            System.out.format("%2d", i+1);
            System.out.print(" "+bordes);
            for (int tab=0; tab< tablero.length;tab++){
                if (tablero[i][tab]==agua) {
                    System.out.print(" "+agua3);
                    System.out.print(" "+bordes);
                }else if (tablero [i] [tab] ==barcou) {
                    System.out.print(" "+barco2);
                    System.out.print(bordes);
                }else {
                    System.out.print(" "+agua2);
                    System.out.print(" "+bordes);
                }
            }
            System.out.println();
        }
        System.out.println("y");
    }

}
