package views;

import controllers.ContentController;
import dataStructures.Contenido;
import dataStructures.Libro;
import helpers.CapituloHelper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaCrearLibro extends VistaCrear {
    private JLabelWhite numPaginas = new JLabelWhite("Número de páginas");
    private JTextField numPaginasField = new JTextField(20);
    private JLabelWhite capMuestra = new JLabelWhite("Cápitulo de muestra");
    private JTextField capMuestraField = new JTextField(20);
    private JButton capMuestraButton = new JButton("Seleccionar");
    private JButton guardar = new JButton("Guardar");
    private JFileChooser fc = new JFileChooser();


    public VistaCrearLibro(Libro Libro) {
        super(Libro);
        grid1.rowHeights=new int[]{100, 100, 100, 100, 100, 50};
        panelContenido.setLayout(grid1);
        crearTextFieldsLibro(Libro);
        capSelector();
        gbc1.gridwidth = 2;
        crearBotonGuardar(Libro);
        crearBotonCancelar();
    }

    public VistaCrearLibro(){
        super();
        grid1.rowHeights=new int[]{100, 100, 100, 100, 100, 50};
        panelContenido.setLayout(grid1);
        crearTextFieldsLibro();
        capSelector();
        gbc1.gridwidth = 2;
        crearBotonGuardar();
        crearBotonCancelar();
    }

    private void crearTextFieldsLibro() {
        gbc1.gridx = 0;
        panelContenido.add(capMuestra, gbc1);
        gbc1.gridx++;

        panelContenido.add(capMuestraField, gbc1);
        gbc1.gridx++;

        EstilosBotones.setCursor(capMuestraButton);
        EstilosBotones.setColor(capMuestraButton, Color.BLUE);
        panelContenido.add(capMuestraButton, gbc1);
        gbc1.gridx = 0;
        gbc1.gridy++;

        panelContenido.add(numPaginas, gbc1);
        gbc1.gridx++;

        panelContenido.add(numPaginasField, gbc1);
        gbc1.gridy++;
    }

    private void crearTextFieldsLibro(Libro libro) {
        capMuestraField.setText(CapituloHelper.getAbsolutePath(libro));
        numPaginasField.setText(String.valueOf(libro.getNumPag()));
        crearTextFieldsLibro();
    }

    private void capSelector(){
        capMuestraButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt",
                        "pdf", "doc", "docx", "rtf", "odt");
                fc.addChoosableFileFilter(filter);int response = fc.showOpenDialog(null);
                try{
                    if (response == JFileChooser.APPROVE_OPTION) {
                        String pathName = fc.getSelectedFile().getPath();
                        capMuestraField.setText(pathName);
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

    private void crearBotonGuardar(Libro libro){
        gbc1.gridx = 0;
        EstilosBotones.setColor(guardar, Color.GREEN);
        EstilosBotones.setCursor(guardar);
        panelContenido.add(guardar, gbc1);
        gbc1.gridx++;
        guardar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContentController contentController = new ContentController();
                contentController.actualizarContenido(getDataUpdate(libro, getData()));
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

    Libro getData(){
        Contenido contenido = super.getData();
        int numPag = 0;
        String rutaCapMuestra = capMuestraField.getText();

        try{
            numPag = Integer.parseInt(numPaginasField.getText());
        } catch (NumberFormatException e){
            pintar("Se ha producido un error.");
            throw e;
        }

        return new Libro(contenido, numPag, rutaCapMuestra);
    }

    private Libro getDataUpdate(Libro old, Libro nueva) {
        return new Libro(old.getPk(), nueva.getTitulo(), nueva.getCodigo(), nueva.getImagen(), nueva.getFechaCreacion(),
                nueva.getStock(), nueva.isResevado(), old.getPkLibro(), nueva.getNumPag(), nueva.getCapituloMuestra());
    }
}
