package com.Talessa;

import com.Talessa.Joc.Tablero;

public class Main {

    public static void main(String[] args) {
          Tablero tablero = new Tablero(11 ,5);
          tablero.iniciarTablero();
          tablero.colBarcos();
          tablero.pintar();
//        int opcion = new Menu().Inici();
//        switch (opcion){
//            case 1:
//
//                break;
//            case 2: System.exit(0);
//        }
    }

}
