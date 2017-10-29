package views;

import controllers.ContentController;
import dataStructures.Contenido;
import helpers.ImageHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

abstract class VistaCrear extends VistaPrincipal {
    private JLabelWhite titulo = new JLabelWhite("Titulo");
    private JLabelWhite codigo = new JLabelWhite("Código");
    private JTextField tituloField = new JTextField(10);
    private JTextField codigoField = new JTextField(10);
    private JLabelWhite stock = new JLabelWhite("Stock");
    private JTextField stockField = new JTextField(10);
    private JLabelWhite imagen = new JLabelWhite("Imagen");
    private JTextField imagenField = new JTextField(20);
    private JButton imagenButton = new JButton("Seleccionar");
    private JFileChooser fc = new JFileChooser();
    private JButton cancelar = new JButton("Cancelar");


    VistaCrear(Contenido contenido) {
        super.setSize(1000,600);
        super.crearPanel();
        crearMenu();
        grid1.columnWidths=new int[]{250,250,250};
        panelContenido.setLayout(grid1);
        crearTextFields(contenido);
        imageSelector();
    }

    VistaCrear() {
        super.setSize(1000,600);
        super.crearPanel();
        crearMenu();
        grid1.columnWidths=new int[]{250,250,250};
        panelContenido.setLayout(grid1);
        crearTextFields();
        imageSelector();
    }

    private void crearTextFields(){
        gbc1.gridy = 0;
        gbc1.gridx = 0;
        gbc1.weightx = 1;

        panelContenido.add(titulo, gbc1);
        gbc1.gridx++;

        panelContenido.add(codigo, gbc1);
        gbc1.gridx++;

        panelContenido.add(stock, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy++;

        panelContenido.add(tituloField, gbc1);
        gbc1.gridx++;

        panelContenido.add(codigoField, gbc1);
        gbc1.gridx++;

        panelContenido.add(stockField, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy++;

        panelContenido.add(imagen, gbc1);
        gbc1.gridx++;

        gbc1.weightx = 3;
        panelContenido.add(imagenField, gbc1);
        gbc1.gridx++;
        gbc1.weightx = 1;

        EstilosBotones.setCursor(imagenButton);
        EstilosBotones.setColor(imagenButton, Color.BLUE);
        panelContenido.add(imagenButton, gbc1);

        gbc1.gridy++;
    }

    private void crearTextFields(Contenido contenido){
        tituloField.setText(contenido.getTitulo());
        codigoField.setText(contenido.getCodigo());
        stockField.setText(String.valueOf(contenido.getStock()));
        imagenField.setText(ImageHelper.getAbsolutePath(contenido));
        crearTextFields();
    }

    private void imageSelector(){
        imagenButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
                fc.addChoosableFileFilter(filter);int response = fc.showOpenDialog(null);
                try{
                    if (response == JFileChooser.APPROVE_OPTION) {
                        String pathName = fc.getSelectedFile().getPath();
                        imagenField.setText(pathName);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
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

    void crearBotonCancelar(){
        cancelar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContentController contentController = new ContentController();
                contentController.initHome();
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
        EstilosBotones.setColor(cancelar, Color.RED);
        EstilosBotones.setCursor(cancelar);
        panelContenido.add(cancelar, gbc1);
    }

    Contenido getData(){
        String titulo = tituloField.getText();
        String codigo = codigoField.getText();
        String imagen = imagenField.getText();
        String stockText = stockField.getText();

        int stock = 0;
        try {
             Integer.parseInt(stockText);
        } catch (NumberFormatException e){
            pintar("Debe introducir un número entero en stock.");
            throw e;
        }

        return new Contenido(titulo, codigo, imagen, stock);
    }
}
