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
    private GridBagConstraints gbc=super.getGbc1();
    
  
    public VistaPelicula(Pelicula pelicula) {
        super(pelicula);
        //PRUEBA MAIN
//        VistaPelicula vp = new VistaPelicula(new Pelicula("Hola", "Hola", "RUTAIMAGEN", 1, true, new Productora("Hola"),new Director("Hola"), new ArrayList<Actor>() {{
//        add(new Actor("Juan"));
//        add(new Actor("Jose"));
//    }}));
        Image imagen = ImageHelper.getImagen(pelicula.getImagen()).getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagen);
        super.setImagen(new JLabel(imagenFinal));
        
        gbc.gridx=1;
        gbc.gridy=1;
        panelContenido.add(new JLabel("Productora: "+pelicula.getProductora().getNombre()), gbc);

        
        gbc.gridy++;
        panelContenido.add(new JLabel("Director: "+pelicula.getDirector().getNombre()), gbc);
        
        gbc.gridx=2;
        gbc.gridy=1;
        panelContenido.add(new JLabel("Actores: "), gbc);
        
        crearActores(pelicula.getActores());
    }



    private void crearActores(java.util.List<Actor> listaActores) {
        for (Actor actor : listaActores) {
            gbc.gridy++;
            panelContenido.add(new JLabel(actor.getNombre()), gbc);
        }
    }
}
