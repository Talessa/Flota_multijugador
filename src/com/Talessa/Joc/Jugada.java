package com.Talessa.Joc;

import java.util.HashMap;
import java.util.Map;

public class Jugada {
    int jugador;
    //0-jugador nuevo
    //1-hygador 1
    //2-jugador 2
    int estado;
    //0-espera
    //1-turno jugador 1
    //2-turno jugador 2
    //3-Fin partida
    //4-Servidor lleno
    int resultado;
    //0-espera
    //1-agua
    //2-barco
    //3-gana jugador 1
    //4-gana jugador 2
    Map<Integer[],Integer> barcos = new HashMap<>();
    //map con los barcos del tablero
    Integer [] jugada = new Integer[2];
    // array con la jugada introducida por el usuario,

    int totalbarcos=5;

    public Jugada(Map<Integer[], Integer> barcos) {
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

    public Map<Integer[], Integer> getBarcos() {
        return barcos;
    }

    public void setBarcos(Map<Integer[], Integer> barcos) {
        this.barcos = barcos;
    }

    public Integer[] getJugada() {
        return jugada;
    }

    public void setJugada(Integer[] jugada) {
        this.jugada = jugada;
    }

    public void setJugada0(int jugada0){
        this.jugada[0] = jugada0;
    }

    public void setJugada1(int jugada1){
        this.jugada[1]=jugada1;
    }

    public int getTotalbarcos() {
        return totalbarcos;
    }

    public void setTotalbarcos(int totalbarcos) {
        this.totalbarcos = totalbarcos;
    }
}
