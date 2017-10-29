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
    private JPanel panel=super.getPanel2();
    private GridBagLayout grid=super.getGrid2();
    private GridBagConstraints gbc=super.getGbc2();
    


    public VistaMusica(Musica musica) {
        Image imagen = ImageHelper.getImagen(musica.getImagen()).getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagen);
        super.setImagen(new JLabel(imagenFinal));
        
        gbc.gridx=0;
        gbc.gridy=2;
        panel.add(new JLabel("Discográfica: "+musica.getDiscografica().getNombre()), gbc);
        
        gbc.gridy=3;
        panel.add(new JLabel("Canciones: "), gbc);
        
        crearCanciones(musica.getCanciones());
    }



    private void crearCanciones(java.util.List<Cancion> listaCanciones) {
        for (Cancion cancion : listaCanciones) {
            gbc.gridy++;
            panel.add(new JLabel(cancion.getNombre()), gbc);
        }
    }
}
