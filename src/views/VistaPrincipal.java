package views;
import helpers.ImageHelper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public abstract class VistaPrincipal extends JFrame{
    //panel
    private JPanel panel = new JPanel();
    //imagen
    private Image imagenLogo = ImageHelper.getLogo();
    //nombre app
    private static final String NOMBREAPLICACION= "Nombre Aplicacion";
    private JOptionPane mensajeError = new JOptionPane();

    public void crearPanel(){
        setTitle(NOMBREAPLICACION);
        setIconImage(imagenLogo);
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        super.setResizable(false);
    }
    //getter
    public JPanel getPanel() {
        return panel;
    }

    public void pintar(List<String> mensaje) {
        String cadenaFinal="";
        for (Object object : mensaje) {
            String cadena;
            cadena = (String) object;
            cadenaFinal += cadena + "\n";
        }
        mensajeError.showMessageDialog(this , cadenaFinal  ,"Error", JOptionPane.ERROR_MESSAGE);
    }

    public void pintar(String mensaje){
        mensajeError.showMessageDialog(this , mensaje ,"Error",JOptionPane.ERROR_MESSAGE);
    }

    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
    }
}
