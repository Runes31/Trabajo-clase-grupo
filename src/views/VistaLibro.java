package views;

import dataStructures.Libro;
import helpers.CapituloHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class VistaLibro extends VistaElemento{
    private GridBagConstraints gbc=super.getGbc1();
    


    public VistaLibro(Libro libro) {
        super(libro);
        
        gbc.gridx=1;
        gbc.gridy=2;
        panelContenido.add(new JLabelWhite("Número de páginas: "+libro.getNumPag()), gbc);
        
        
        //CUIDADO CON ESTO
        gbc.gridy=3;
        JButton verCapMuestra = new JButton("Ver capítulo de muestra");
        EstilosBotones.botonPrimary(verCapMuestra);
        EstilosBotones.setCursor(verCapMuestra);
        verCapMuestra.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    CapituloHelper.verCapituloMuestra(libro);
                } catch (IOException e1) {
                    pintar("Se ha producido un error.");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panelContenido.add(verCapMuestra, gbc);

    }

}
