package views;

import controllers.ContentController;
import dataStructures.Cancion;
import dataStructures.Contenido;
import dataStructures.Discografica;
import dataStructures.Musica;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class VistaCrearMusica extends VistaCrear {
    private JLabelWhite discografica = new JLabelWhite("Discografica");
    private JTextField discograficaField = new JTextField(20);
    private JLabelWhite canciones = new JLabelWhite("Canciones");
    private JTextField cancionesField = new JTextField(20);
    private JButton guardar = new JButton("Guardar");

    public VistaCrearMusica(){
        super();
        grid1.rowHeights=new int[]{100, 100, 100, 100, 100, 50};
        panelContenido.setLayout(grid1);
        crearTextFieldsMusica();
        gbc1.gridwidth = 2;
        crearBotonGuardar();
        crearBotonCancelar();
    }

    public VistaCrearMusica(Musica musica){
        super(musica);
        grid1.rowHeights=new int[]{100, 100, 100, 100, 100, 50};
        panelContenido.setLayout(grid1);
        crearTextFieldsMusica(musica);
        gbc1.weightx = 3;
        crearBotonGuardar(musica);
        crearBotonCancelar();
    }

    private void crearTextFieldsMusica() {
        gbc1.gridx = 0;
        panelContenido.add(discografica, gbc1);
        gbc1.gridx++;

        gbc1.weightx = 3;
        panelContenido.add(discograficaField, gbc1);
        gbc1.gridy++;
        gbc1.gridx = 0;
        gbc1.weightx = 1;

        panelContenido.add(canciones, gbc1);
        gbc1.gridx++;

        gbc1.weightx = 3;
        panelContenido.add(cancionesField, gbc1);
        gbc1.gridy++;
        gbc1.weightx = 1;
    }

    private void crearTextFieldsMusica(Musica musica){
        discograficaField.setText(musica.getDiscografica().getNombre());
        StringBuilder canciones = new StringBuilder();
        for (Cancion c : musica.getCanciones()){
            canciones.append(c.getNombre()).append(", ");
        }

        canciones = new StringBuilder(canciones.substring(0, canciones.lastIndexOf(", ")));

        cancionesField.setText(canciones.toString());
        crearTextFieldsMusica();
    }

    private void crearBotonGuardar() {
        guardar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContentController contentController = new ContentController();
                contentController.crearContenido(getData());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        EstilosBotones.setCursor(guardar);
        EstilosBotones.botonSuccess(guardar);
        gbc1.gridx = 0;
        panelContenido.add(guardar, gbc1);
        gbc1.gridx++;
    }

    private void crearBotonGuardar(Musica musica){
        guardar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContentController contentController = new ContentController();
                contentController.actualizarContenido(getDataUpdate(musica, getData()));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        EstilosBotones.setCursor(guardar);
        EstilosBotones.botonSuccess(guardar);
        gbc1.gridx = 0;
        gbc1.weightx = 2;
        panelContenido.add(guardar, gbc1);
        gbc1.gridx++;
    }

    Musica getData(){
        Contenido contenido = super.getData();
        Discografica discografica = new Discografica(discograficaField.getText());
        List<Cancion> cancionList = new ArrayList<>();

        String[] canciones = cancionesField.getText().split(",");

        int i = 1;
        for (String s : canciones){
            Cancion cancion = new Cancion(s.trim(), i++);
            cancionList.add(cancion);
        }

        return new Musica(contenido, discografica, cancionList);
    }

    private Musica getDataUpdate(Musica old, Musica nueva){
        return new Musica(old.getPkContenido(), nueva.getTitulo(), nueva.getCodigo(), nueva.getImagen(), nueva.getFechaCreacion(),
                nueva.getStock(), nueva.isResevado(), old.getPkMusica(), nueva.getDiscografica(), nueva.getCanciones());
    }
}