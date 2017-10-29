package views;

import dataStructures.Actor;
import dataStructures.Pelicula;

import java.awt.*;

public class VistaPelicula extends VistaElemento{
    private GridBagConstraints gbc=super.getGbc1();
    
  
    public VistaPelicula(Pelicula pelicula) {
        super(pelicula);
        
        gbc.gridx=1;
        gbc.gridy=1;
        panelContenido.add(new JLabelWhite("Productora: "+pelicula.getProductora().getNombre()), gbc);

        
        gbc.gridy++;
        panelContenido.add(new JLabelWhite("Director: "+pelicula.getDirector().getNombre()), gbc);
        
        gbc.gridx=2;
        gbc.gridy=1;
        panelContenido.add(new JLabelWhite("Actores: "), gbc);
        
        crearActores(pelicula.getActores());
    }



    private void crearActores(java.util.List<Actor> listaActores) {
        for (Actor actor : listaActores) {
            gbc.gridy++;
            panelContenido.add(new JLabelWhite(actor.getNombre()), gbc);
        }
    }
}
