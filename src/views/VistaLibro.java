package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataStructures.*;
import helpers.ImageHelper;

public class VistaLibro extends VistaElemento{
    private GridBagConstraints gbc;
    


    public VistaLibro(Libro libro) {
        super(libro);
        
        gbc.gridx=1;
        gbc.gridy=2;
        panelContenido.add(new JLabel("Número de páginas: "+libro.getNumPag()), gbc);
        
        
        //CUIDADO CON ESTO
        gbc.gridy=3;
        panelContenido.add(new JLabel("Capítulo de muestra: "+libro.getCapituloMuestra()), gbc);

    }

}
