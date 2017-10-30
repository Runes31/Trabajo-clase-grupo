package views;

import dataStructures.Cancion;
import dataStructures.Musica;

import java.awt.*;
import java.util.Collections;

public class VistaMusica extends VistaElemento{
    private GridBagConstraints gbc=super.getGbc1();
    


    public VistaMusica(Musica musica) {
        super(musica);
        titulo.setText(titulo.getText() + "\n\n" + "Discográfica: " + musica.getDiscografica().getNombre());
        gbc.gridwidth = 2;
        panelContenido.add(new JLabelWhite("Canciones: "), gbc);
        gbc.gridwidth = 1;
        gbc.gridy++;
        crearCanciones(musica.getCanciones());
    }



    private void crearCanciones(java.util.List<Cancion> listaCanciones) {
        Collections.sort(listaCanciones);
        int i = 1;
        int y = gbc.gridy;
        for (Cancion cancion : listaCanciones) {
            panelContenido.add(new JLabelWhite(cancion.getOrden()+". " + cancion.getNombre()), gbc);
            gbc.gridy++;
            i++;
            if(i == 5){
                gbc.gridy = y;
                gbc.gridx++;
            }
        }
    }
}
