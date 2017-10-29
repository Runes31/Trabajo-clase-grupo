package views;

import controllers.ContentController;
import dataStructures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VistaCrearPelicula extends VistaCrear {
    private JLabelWhite productora = new JLabelWhite("Productora");
    private JTextField productoraField = new JTextField(20);
    private JLabelWhite director = new JLabelWhite("Director");
    private JTextField directorField = new JTextField(20);
    private JLabelWhite actores = new JLabelWhite("Actores");
    private JTextField actoresField = new JTextField(20);
    private JButton guardar = new JButton("Guardar");


    public VistaCrearPelicula(Pelicula pelicula) {
        super(pelicula);
        grid1.rowHeights=new int[]{80, 80, 80, 80, 80, 80, 50};
        panelContenido.setLayout(grid1);
        gbc1.gridwidth = 2;
        crearTextFieldsPelicula(pelicula);
        crearBotonGuardar(pelicula);
        crearBotonCancelar();
    }

    public VistaCrearPelicula(){
        super();
        grid1.rowHeights=new int[]{80, 80, 80, 80, 80, 80, 50};
        panelContenido.setLayout(grid1);
        gbc1.gridwidth = 2;
        crearTextFieldsPelicula();
        crearBotonGuardar();
        crearBotonCancelar();
    }

    private void crearTextFieldsPelicula() {
        gbc1.gridx = 0;
        panelContenido.add(director, gbc1);
        gbc1.gridx++;

        panelContenido.add(productora, gbc1);
        gbc1.gridx = 0;
        gbc1.gridy++;

        panelContenido.add(directorField, gbc1);
        gbc1.gridx++;

        panelContenido.add(productoraField, gbc1);
        gbc1.gridx = 0;
        gbc1.gridy++;

        panelContenido.add(actores, gbc1);
        gbc1.gridx++;

        panelContenido.add(actoresField, gbc1);
        gbc1.gridy++;
    }

    private void crearTextFieldsPelicula(Pelicula pelicula) {
        directorField.setText(pelicula.getDirector().getNombre());
        productoraField.setText(pelicula.getProductora().getNombre());

        StringBuilder actores = new StringBuilder();
        for (Actor a : pelicula.getActores()){
            actores.append(a.getNombre()).append(", ");
        }

        actores = new StringBuilder(actores.substring(0, actores.lastIndexOf(", ")));
        actoresField.setText(actores.toString());

        crearTextFieldsPelicula();
    }

    private void crearBotonGuardar() {
        gbc1.gridx = 0;
        EstilosBotones.setColor(guardar, Color.GREEN);
        EstilosBotones.setCursor(guardar);
        panelContenido.add(guardar, gbc1);
        gbc1.gridx++;
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
    }

    private void crearBotonGuardar(Pelicula pelicula){
        gbc1.gridx = 0;
        EstilosBotones.setColor(guardar, Color.GREEN);
        EstilosBotones.setCursor(guardar);
        panelContenido.add(guardar, gbc1);
        gbc1.gridx++;
        guardar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContentController contentController = new ContentController();
                contentController.actualizarContenido(getDataUpdate(pelicula, getData()));
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
    }

    Pelicula getData(){
        Contenido contenido = super.getData();
        Productora productora = new Productora(productoraField.getText());
        Director director = new Director(directorField.getText());

        String[] actores = actoresField.getText().split(",");

        java.util.List<Actor> actorList = new ArrayList<>();

        for (String s : actores){
            Actor actor = new Actor(s.trim());
            actorList.add(actor);
        }

        return new Pelicula(contenido, productora, director, actorList);
    }

    private Pelicula getDataUpdate(Pelicula old, Pelicula nueva) {
        return new Pelicula(old.getPk(), nueva.getTitulo(), nueva.getCodigo(), nueva.getImagen(), nueva.getFechaCreacion(),
                nueva.getStock(), nueva.isResevado(), old.getPkPelicula(), nueva.getProductora(), nueva.getDirector(),
                nueva.getActores());
    }
}
