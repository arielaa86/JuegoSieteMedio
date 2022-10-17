/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sietemedio.gui;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sietemedio.Carta;
import sietemedio.Juego;
import sietemedio.Jugador;

/**
 *
 * @author paloitparis
 */
public class Principal extends javax.swing.JFrame {

    private boolean mostrarResultados;

    /**
     * Creates new form Principal
     *
     * @param juego
     */
    public Principal(Juego juego) {
        mostrarResultados = false;
        Font arialBold22 = new Font("Arial", Font.BOLD, 22);
        Font arialBold32 = new Font("Arial", Font.BOLD, 32);

        initComponents();

        GridLayout layoutPrincipal = new GridLayout(4, 1);
        GridLayout layoutPanelBanca = new GridLayout(1, 1);
        GridLayout layoutPanelCartas = new GridLayout(1, 11);
        GridLayout layoutPanelBotones = new GridLayout(1, 2);

        this.setLayout(layoutPrincipal);

        JLabel nombreJugadorLabel = new JLabel(" Jugador en turno: " + juego.getJugadorEnTurno().getNombre() + "  Total: " + juego.getJugadorEnTurno().getValorTotalDeCartas());
        nombreJugadorLabel.setFont(arialBold32);

        JPanel panelBanca = new JPanel();
        panelBanca.setLayout(layoutPanelBanca);
        panelBanca.add(new Carta("cartas/carta.png", 0));

        JPanel panelCartas = new JPanel();
        panelCartas.setLayout(layoutPanelCartas);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(layoutPanelBotones);

        JButton botonPlantarse = new JButton("Plantarme");
        botonPlantarse.setFont(arialBold22);
        JButton botonPedirCarta = new JButton("Pedir carta");
        botonPedirCarta.setFont(arialBold22);
        JButton botonVerJugador = new JButton("Ver proximo jugador");
        botonVerJugador.setFont(arialBold22);

        //Escuchar el evento click del botón para plantarse
        botonPlantarse.addActionListener((e) -> {

            if (!mostrarResultados) {

                botonPedirCarta.setEnabled(true);

                juego.getJugadorEnTurno().setHaTerminado(true);
                Jugador jugadorEnTurno = juego.getProximoJugador();

                nombreJugadorLabel.setText(" Jugador en turno: " + jugadorEnTurno.getNombre() + "  Total: " + jugadorEnTurno.getValorTotalDeCartas());

                panelCartas.removeAll();
                panelCartas.setVisible(false);

                jugadorEnTurno.getCartas().forEach(carta -> panelCartas.add(carta));

                panelCartas.setVisible(true);

                if (juego.getJugadorEnTurno().haTerminado()) {
                    botonPedirCarta.setEnabled(false);
                } else {
                    botonPedirCarta.setEnabled(true);
                }

                if (juego.hanTerminadoTodosLosJugadores()) {
                    botonPlantarse.setText("Ver Resultados");
                    mostrarResultados = true;
                    botonPlantarse.setEnabled(true);
                }
                
                if(jugadorEnTurno.getCartas().isEmpty()){
                    botonPlantarse.setEnabled(false);
                }
              
            } else {
                VentanaResultados ventanaResultados = new VentanaResultados(this, juego);
                ventanaResultados.setLocationRelativeTo(null);
                ventanaResultados.setVisible(true);
            }

        });

        //Escuchar el evento click del botón para pedir otra carta
        botonPedirCarta.addActionListener((e) -> {
            //Para forzar que se vuelva a actualizar la ventana cambiamos la visivilidad
            Jugador jugadorEnTurno = juego.getJugadorEnTurno();
            if (!jugadorEnTurno.haTerminado()) {

                //verificamos que el jugador no ha terminado no ha terminado
                panelCartas.setVisible(false);
                Carta cartaEnJuego = juego.getProximaCarta();

                if (cartaEnJuego != null) {
                    jugadorEnTurno.getCartas().add(cartaEnJuego);
                    jugadorEnTurno.getCartas().forEach(carta -> panelCartas.add(carta));
                    nombreJugadorLabel.setText(" Jugador en turno: " + jugadorEnTurno.getNombre() + "  Total: " + jugadorEnTurno.getValorTotalDeCartas());

                } else {
                    panelBanca.setVisible(false);
                    panelBanca.removeAll();
                    panelBanca.add(new Carta("cartas/no_cartas.png", 0));
                    panelBanca.setVisible(true);
                    JOptionPane.showMessageDialog(this, "No hay más cartas");
                }

                panelCartas.setVisible(true);
            }

            if (jugadorEnTurno.haTerminado()) {
                botonPedirCarta.setEnabled(false);
            } else {
                botonPedirCarta.setEnabled(true);
            }

            if (!jugadorEnTurno.getCartas().isEmpty()) {
                botonPlantarse.setEnabled(true);
            } else {
                botonPlantarse.setEnabled(false);
            }

        });

        botonPlantarse.setEnabled(false);

        panelBotones.add(botonPlantarse);
        panelBotones.add(botonPedirCarta);

        this.add(nombreJugadorLabel);
        this.add(panelBanca);
        this.add(panelCartas);
        this.add(panelBotones);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
