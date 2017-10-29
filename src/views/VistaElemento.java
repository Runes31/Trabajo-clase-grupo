package views;

import dataStructures.Contenido;
import helpers.ImageHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public abstract class VistaElemento extends VistaPrincipal{
    private JLabel imagen = new JLabel("", JLabel.CENTER);
    private JLabel titulo =new JLabel();
    private JLabel codigo =new JLabel();
    private JButton botonAlquilar= new JButton("Alquilar");
    private GridBagConstraints gbc1= super.gbc1;
    
    

    public GridBagConstraints getGbc1() {
        return gbc1;
    }

    public void setGbc1(GridBagConstraints gbc1) {
        this.gbc1 = gbc1;
    }

    public VistaElemento(Contenido contenido) {
        super.setSize(1000,600);
        super.crearPanel();

        crearMenu();

        setVariablesContenido(contenido);

        //GridX = 0
        super.panelContenido.setPreferredSize(new Dimension(700,600));
        grid1.columnWidths=new int[]{400,200,200};

        crearPanel1();

        
        //GridX = 1
        panelContenido.setBackground(Color.white);
    }

    private void setVariablesContenido(Contenido contenido) {
        Image img = ImageHelper.getImagen(contenido.getImagen()).getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        imagen.setIcon(icon);
        titulo.setText("Título: " + contenido.getTitulo());
        codigo.setText("Código: " + contenido.getCodigo());
    }

    private void crearPanel1() {
        grid1.rowHeights=new int []{300,45,45,45,45,45,45};
        gbc1.gridx=0;
        gbc1.gridy = 0;
        panelContenido.add(imagen, gbc1);
        
        gbc1.insets= new Insets(30, 0, 0, 0);
        gbc1.gridy=1;
        panelContenido.add(codigo, gbc1);
        gbc1.gridy++;
        botonAlquilar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
        panelContenido.add(botonAlquilar, gbc1);
        
        gbc1.gridx=1;
        gbc1.weighty = 1;
        gbc1.gridy = 0;
        gbc1.gridwidth=2;
        panelContenido.add(titulo, gbc1);
        
        gbc1.gridwidth=1;
        gbc1.gridy = 1;

    }
    
    
    
    public JLabel getImagen() {
        return imagen;
    }

    public void setImagen(JLabel imagen) {
        this.imagen = imagen;
    }

    public JLabel getTitulo() {
        return titulo;
    }
    
    public void setTitulo(JLabel titulo) {
        this.titulo = titulo;
    }
    
    public JLabel getCodigo() {
        return codigo;
    }
    
    public void setCodigo(JLabel codigo) {
        this.codigo = codigo;
    }
    



}
