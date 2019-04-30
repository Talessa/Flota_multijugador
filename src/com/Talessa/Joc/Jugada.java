package com.Talessa.Joc;

import java.util.HashMap;
import java.util.Map;

public class Jugada {
    int jugador;
    int estado;
    int resultado;
    Map<Integer,Integer[]> barcos = new HashMap<>();
    int [] jugada = new int[2];

    public Jugada(Map<Integer, Integer[]> barcos) {
        this.jugador = 0;
        this.estado = 0;
        this.resultado = 0;
        this.barcos = barcos;
        this.jugada[0]=99;
        this.jugada[1]=99;
    }

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public Map<Integer, Integer[]> getBarcos() {
        return barcos;
    }

    public void setBarcos(Map<Integer, Integer[]> barcos) {
        this.barcos = barcos;
    }

    public int[] getJugada() {
        return jugada;
    }

    public void setJugada(int[] jugada) {
        this.jugada = jugada;
    }

    public void setJugada0(int jugada0){
        this.jugada[0] = jugada0;
    }

    public void setJugada1(int jugada1){
        this.jugada[1]=jugada1;
    }

}
