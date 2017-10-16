package views;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class VistaLogin extends VistaPrincipal{
	//layout
	private GridBagLayout grid = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	
	private JLabel labelUsuario = new JLabel("Usuario");
	private JTextField usuario = new JTextField(10);
	private JLabel labelPassword = new JLabel("Contraseña");
	private JPasswordField password= new JPasswordField(10);
	private JButton botonLogin = new JButton("Login");
	private JButton botonRegistrarse = new JButton("Registrarse");
	
	public VistaLogin(){
		super.crearPanel();
		super.setSize(400,300);
		super.setLocationRelativeTo(null);
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
		
		c.gridy=5;
		super.getPanel().add(botonRegistrarse,c);
		
		botonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean comprobacion;
//				comprobarUsuario(obtenerUsuario(),obtenerContraseña());
//				if(comprobacion)
//					Llamada a la vista principal
				
			}
		});
		
		botonRegistrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				llamada a la vista registrarse
				
			}
		});
		
	}

	private String obtenerUsuario(){
		String resultado;
		resultado = usuario.getText();
		return resultado;
	}
	
	public String obtenerContraseña(){
		char[] cadena = password.getPassword();
		String resultado= new String(cadena);
		return resultado;	
	}
	
	
}
