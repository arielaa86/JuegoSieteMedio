/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sietemedio;

import javax.swing.JFrame;
import sietemedio.gui.Principal;
import sietemedio.gui.VentanaJugadores;

/**
 *
 * @author paloitparis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         VentanaJugadores ventanaJugadores = new VentanaJugadores();
         ventanaJugadores.setResizable(false);
         ventanaJugadores.setLocationRelativeTo(null);
         ventanaJugadores.setVisible(true);
        
     
    }
    
}
