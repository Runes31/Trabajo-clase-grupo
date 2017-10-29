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
    private JPanel panel=super.getPanel2();
    private GridBagLayout grid=super.getGrid2();
    private GridBagConstraints gbc=super.getGbc2();
    


    public VistaLibro(Libro libro) {
        Image imagen = ImageHelper.getImagen(libro.getImagen()).getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagen);
        super.setImagen(new JLabel(imagenFinal));
        
        gbc.gridx=0;
        gbc.gridy=2;
        panel.add(new JLabel("Número de páginas: "+libro.getNumPag()), gbc);
        
        
        //CUIDADO CON ESTO
        gbc.gridy=3;
        panel.add(new JLabel("Capítulo de muestra: "+libro.getCapituloMuestra()), gbc);

    }

}
