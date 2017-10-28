package views;

import java.awt.GridBagConstraints;
import java.awt.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dataStructures.*;

public class VistaPelicula extends VistaElemento{
    private String productora;
    private String Director;
    private List actores;
    private JPanel panel=super.getPanel2();
    private GridBagConstraints gbc= super.getGbc2();
    
    
    public VistaPelicula(Pelicula pelicula) {
        gbc.gridx=0;
        gbc.gridy=1;
        panel.add(new JLabel("Productora "+pelicula.getProductora().getNombre()), gbc);
    }
}
