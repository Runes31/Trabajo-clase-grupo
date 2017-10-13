package views;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class VistaLogin extends VistaPrincipal{
	//layout
	private GridBagLayout grid = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	
	private JLabel labelUsuario = new JLabel("Hola");
	private JTextField usuario = new JTextField("pepito");
	private JLabel labelPassword = new JLabel("adios");
	private JTextField password= new JTextField("juanito");
	private JButton botonLogin = new JButton("Login");
	
	public VistaLogin(){
		super.crearPanel();
		super.getPanel().setLayout(grid);
		c.weighty=0.1;
		c.gridheight=1;
		
		c.gridx=0;
		c.gridy=0;
		super.getPanel().add(labelUsuario,c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridy=1;
		super.getPanel().add(usuario,c);
		
		c.gridy=2;
		super.getPanel().add(labelPassword,c);
		
		c.gridy=3;
		super.getPanel().add(password,c);
		
		c.gridy=4;
		super.getPanel().add(botonLogin,c);
		
	}
}
