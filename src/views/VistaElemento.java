package views;

import controllers.ContentController;
import controllers.MainController;
import controllers.PrestamosController;
import controllers.UserController;
import dataStructures.Contenido;
import dataStructures.Libro;
import dataStructures.Musica;
import dataStructures.Pelicula;
import helpers.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class VistaElemento extends VistaPrincipal{
    JLabelWhite imagen = new JLabelWhite("");
    JTextArea titulo =new JTextArea(5, 50);
    JLabelWhite codigo =new JLabelWhite();
    JButton botonAlquilar= new JButton();
    GridBagConstraints gbc1= super.gbc1;
    JButton botonBorrar = new JButton("Borrar");

    GridBagConstraints getGbc1(){return gbc1;}

    public VistaElemento(Contenido contenido) {
        super.setSize(1000,600);
        super.crearPanel();

        crearMenu();
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(false);
        titulo.setEditable(false);
        titulo.setLineWrap(true);
        titulo.setWrapStyleWord(true);
        Font font = titulo.getFont();
        titulo.setFont(font.deriveFont(Font.BOLD));
        setVariablesContenido(contenido);

        super.panelContenido.setPreferredSize(new Dimension(700,600));
        grid1.columnWidths=new int[]{400,200,200};

        crearPanel1(contenido);
    }

    private void setVariablesContenido(Contenido contenido) {
        Image img = ImageHelper.getImagen(contenido.getImagen()).getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        imagen.setIcon(icon);
        titulo.setText("Título: " + contenido.getTitulo());
        codigo.setText("Código: " + contenido.getCodigo());
    }

    private void crearPanel1(Contenido contenido) {
        grid1.rowHeights=new int []{300,45,45,45,45,45};
        gbc1.gridx=0;
        gbc1.gridy = 0;
        gbc1.insets= new Insets(30, 0, 0, 0);
        panelContenido.add(imagen, gbc1);
        
        gbc1.insets= new Insets(30, 0, 0, 0);
        gbc1.gridy=1;
        panelContenido.add(codigo, gbc1);
        gbc1.gridy++;
        crearBoton(contenido);

        if(UserController.getCurrentUser().esAdmin())
            crearBotonBorrar(contenido);
        
        gbc1.gridx=1;
        gbc1.weighty = 1;
        gbc1.gridy = 0;
        gbc1.gridwidth=2;
        gbc1.fill = GridBagConstraints.BOTH;
        panelContenido.add(titulo, gbc1);
        gbc1.fill = GridBagConstraints.CENTER;
        
        gbc1.gridwidth=1;
        gbc1.gridy = 1;

    }

    private void crearBoton(Contenido contenido) {
        EstilosBotones.setCursor(botonAlquilar);
        if (UserController.getCurrentUser().esAdmin()) {
            botonAlquilar.setText("Editar");
            EstilosBotones.botonPrimary(botonAlquilar);
            botonAlquilar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(contenido instanceof Musica)
                        MainController.setView(new VistaCrearMusica((Musica) contenido));
                    else if(contenido instanceof Pelicula)
                        MainController.setView(new VistaCrearPelicula((Pelicula) contenido));
                    else
                        MainController.setView(new VistaCrearLibro((Libro) contenido));
                }
            });
        } else if (contenido.isResevado()){
            botonAlquilar.setText("Devolver");
            EstilosBotones.botonDanger(botonAlquilar);
            botonAlquilar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    PrestamosController prestamosController = new PrestamosController();
                    prestamosController.devolverPrestamo(contenido);
                }
            });

        } else {
            botonAlquilar.setText("Alquilar");
            EstilosBotones.botonSuccess(botonAlquilar);
            botonAlquilar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    PrestamosController prestamosController = new PrestamosController();
                    prestamosController.hacerPrestamo(contenido);
                }
            });
        }
        panelContenido.add(botonAlquilar, gbc1);
    }

    private void crearBotonBorrar(Contenido contenido){
        gbc1.gridy++;
        EstilosBotones.botonDanger(botonBorrar);
        EstilosBotones.setCursor(botonBorrar);

        botonBorrar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContentController contentController = new ContentController();
                contentController.borrarContenido(contenido);
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

        panelContenido.add(botonBorrar, gbc1);
    }
}