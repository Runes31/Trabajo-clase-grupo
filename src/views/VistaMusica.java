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
        int i = 0;
        int y = gbc.gridy;
        gbc.insets = new Insets(0, 0, 0, 0);
        while(i<listaCanciones.size() && i<8) {
            panelContenido.add(new JLabelWhite(listaCanciones.get(i).getOrden()+". " + listaCanciones.get(i).getNombre()), gbc);
            gbc.gridy++;
            i++;
            if(i == 4){
                gbc.gridy = y;
                gbc.gridx++;
            }
        }
    }
}
