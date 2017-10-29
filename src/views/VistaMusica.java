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

public class VistaMusica extends VistaElemento{
    private GridBagConstraints gbc;
    


    public VistaMusica(Musica musica) {
        super(musica);
        
        gbc.gridx=1;
        gbc.gridy=2;
        panelContenido.add(new JLabel("Discográfica: "+musica.getDiscografica().getNombre()), gbc);
        
        gbc.gridy=3;
        panelContenido.add(new JLabel("Canciones: "), gbc);
        
        crearCanciones(musica.getCanciones());
    }



    private void crearCanciones(java.util.List<Cancion> listaCanciones) {
        for (Cancion cancion : listaCanciones) {
            gbc.gridy++;
            panelContenido.add(new JLabel(cancion.getNombre()), gbc);
        }
    }
}
