/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sietemedio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paloitparis
 */
public class Jugador {
    
      private String nombre;
      private List<Carta> cartas;
      private boolean haTerminado;

    public Jugador(String nombre) {
        cartas = new ArrayList<>();
        haTerminado = false;
        this.nombre = nombre;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public boolean haTerminado() {
        return getValorTotalDeCartas() > 7.5 || haTerminado;
    }

    public void setHaTerminado(boolean haTerminado) {
        this.haTerminado = haTerminado;
    }

    public String getNombre() {
        return nombre;
    }
    
    public double getValorTotalDeCartas(){
        return cartas.stream().mapToDouble(carta-> carta.getValor()).sum();
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }
    
    

}
