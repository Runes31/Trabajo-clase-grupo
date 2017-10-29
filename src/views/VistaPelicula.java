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

public class VistaPelicula extends VistaElemento{
    private JPanel panel=super.getPanel2();
    private GridBagLayout grid=super.getGrid2();
    private GridBagConstraints gbc=super.getGbc2();
    
  
    public VistaPelicula(Pelicula pelicula) {
        
        //PRUEBA MAIN
//        VistaPelicula vp = new VistaPelicula(new Pelicula("Hola", "Hola", "RUTAIMAGEN", 1, true, new Productora("Hola"),new Director("Hola"), new ArrayList<Actor>() {{
//        add(new Actor("Juan"));
//        add(new Actor("Jose"));
//    }}));
        Image imagen = ImageHelper.getImagen(pelicula.getImagen()).getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagen);
        super.setImagen(new JLabel(imagenFinal));
        
        gbc.gridx=0;
        gbc.gridy=2;
        panel.add(new JLabel("Productora: "+pelicula.getProductora().getNombre()), gbc);

        
        gbc.gridy=3;
        panel.add(new JLabel("Director: "+pelicula.getDirector().getNombre()), gbc);
        
        gbc.gridy=4;
        panel.add(new JLabel("Actores: "), gbc);
        
        crearActores(pelicula.getActores());
    }



    private void crearActores(java.util.List<Actor> listaActores) {
        for (Actor actor : listaActores) {
            gbc.gridy++;
            panel.add(new JLabel(actor.getNombre()), gbc);
        }
    }
}
