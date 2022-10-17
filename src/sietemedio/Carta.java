/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sietemedio;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 *
 * @author paloitparis
 */
public class Carta extends JButton {
    
    private final double valor;

    public Carta(String rutaImagen, double valor) {
        super();
        LineBorder lineBorder = new LineBorder(new Color(117, 175, 255, 0), 1, true);
        this.setBorder(lineBorder);
        Image imagen = new ImageIcon(rutaImagen).getImage();
        Image imagenEscalada = imagen.getScaledInstance(115, 175, java.awt.Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(imagenEscalada));
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
    
    

}
