/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sietemedio;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 * @author paloitparis
 */
public class Juego {

    private List<Carta> cartas;
    private List<Jugador> jugadores;
    private int jugadorEnTurno;

    public Juego() {

        jugadores = new ArrayList<>();
        iniciarJuego();

    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void iniciarJuego() {

        cartas = new ArrayList<>();

        //carpetas con las imagenes de cartas
        File[] carpetas = {new File("cartas/corazon"), new File("cartas/picas"), new File("cartas/diamante"), new File("cartas/trebol")};

        for (File carpeta : carpetas) {

            File[] ficherosImagen = carpeta.listFiles();

            //Por cada fichero de imagen agregar a la lista de cartas la carta con su valor
            for (File file : ficherosImagen) {
                if (file.isFile()) {
                    String nombreImagen = file.getName();
                    String ruta = carpeta + "/" + nombreImagen;
                    cartas.add(new Carta(ruta, getValorCarta(nombreImagen)));
                }
            }
        }

        jugadorEnTurno = 0;

    }

    public Carta getProximaCarta() {
        if (cartas.size() > 0) {
            //tomar una carta de manera aleatoria
            Carta carta = cartas.get(ThreadLocalRandom.current().nextInt(0, cartas.size()));
            cartas.remove(carta);
            return carta;
        }
        return null;
    }

    public Jugador getJugadorEnTurno() {
        return jugadores.get(jugadorEnTurno);
    }

    public Jugador getProximoJugador() {
        if (jugadorEnTurno < jugadores.size() - 1) {
            jugadorEnTurno++;
        } else {
            jugadorEnTurno = 0;
        }

        return jugadores.get(jugadorEnTurno);
    }

    public Jugador getGanador() {

        List<Jugador> lista = jugadores.stream().filter(jugador -> jugador.getValorTotalDeCartas() <= 7.5).collect(Collectors.toList());

        if (!lista.isEmpty()) {
            Jugador ganador = lista.get(0);

            for (int i = 1; i < lista.size(); i++) {
                //buscar el que este mas cerca de 7.5 y en caso que tengan los mismos puntos que tenga menos cartas
                if (lista.get(i).getValorTotalDeCartas() > ganador.getValorTotalDeCartas()) {
                    ganador = lista.get(i);
                } else {

                    if (lista.get(i).getValorTotalDeCartas() == ganador.getValorTotalDeCartas() && lista.get(i).getCartas().size() < ganador.getCartas().size()) {
                        ganador = lista.get(i);
                    }

                }

            }
            return ganador;
        }

        return null;

    }

    public boolean hanTerminadoTodosLosJugadores() {
        return jugadores.stream().filter(jugador -> jugador.haTerminado()).count() == jugadores.size();
    }

    public boolean haTerminadoJugador() {
        return jugadores.get(jugadorEnTurno).haTerminado();
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    private double getValorCarta(String nombreImagen) {

        //eliminar la extension y determinar valor
        String nombre = nombreImagen.split(".png")[0];
        switch (nombre) {
            case "A":
                return 1;
            case "J":
                return 0.5;
            case "Q":
                return 0.5;
            case "K":
                return 0.5;
            default:
                return Double.parseDouble(nombre);
        }

    }

}
