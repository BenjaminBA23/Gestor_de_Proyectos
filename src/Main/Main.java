/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import javax.swing.SwingUtilities;
import vista.VentanaPrincipal;

/**
 *
 * @author Ben
 */
public class Main {
    public static void main(String[] args) {
        // Ejecuta la ventana en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}