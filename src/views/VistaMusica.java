package views;

import dataStructures.Cancion;
import dataStructures.Musica;

import java.awt.*;

public class VistaMusica extends VistaElemento{
    private GridBagConstraints gbc=super.getGbc1();
    


    public VistaMusica(Musica musica) {
        super(musica);
        
        gbc.gridx=1;
        gbc.gridy=2;
        panelContenido.add(new JLabelWhite("Discográfica: "+musica.getDiscografica().getNombre()), gbc);
        
        gbc.gridy=3;
        panelContenido.add(new JLabelWhite("Canciones: "), gbc);
        
        crearCanciones(musica.getCanciones());
    }



    private void crearCanciones(java.util.List<Cancion> listaCanciones) {
        for (Cancion cancion : listaCanciones) {
            gbc.gridy++;
            panelContenido.add(new JLabelWhite(cancion.getNombre()), gbc);
        }
    }
}
