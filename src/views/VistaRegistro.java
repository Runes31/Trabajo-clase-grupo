package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VistaRegistro extends VistaPrincipal{
	private GridBagLayout grid = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	
	private JLabel labelNombreUsuario = new JLabel("Alias: ");
	private JTextField nombreUsuario = new JTextField(10);
	private JLabel labelNombreCompleto = new JLabel("Nombre y Apellidos: ");
	private JTextField nombreCompleto = new JTextField(10);
	private JLabel labelPassword = new JLabel("Contraseña: ");
	private JPasswordField password= new JPasswordField(10);
	private JLabel labelVerificarPassword = new JLabel("Verificar contraseña: ");
	private JPasswordField verificarPassword = new JPasswordField(10);
	private JLabel labelEmail = new JLabel("Email: ");
	private JTextField email = new JTextField(33);
	private JButton botonRegistrarse = new JButton("Registrarse");
	private JButton botonCancelar = new JButton("Cancelar");
	
	
	public VistaRegistro(){
		super.crearPanel();
		super.setSize(550,300);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		
		super.getPanel().setLayout(grid);
		c.weighty=0.1;
		c.gridheight=1;
		c.anchor=c.WEST;
		
		c.gridx=0;
		c.gridy=0;
		super.getPanel().add(labelNombreUsuario,c);
		
		c.gridx=1;
		super.getPanel().add(nombreUsuario,c);
		
		c.anchor=c.EAST;
		c.insets=new Insets(0, 20, 0, 0);
		c.gridx=2;
		super.getPanel().add(labelNombreCompleto,c);
		
		c.anchor=c.WEST;
		
		c.insets= new Insets(0, 0, 0, 0);
		c.gridx=3;
		super.getPanel().add(nombreCompleto,c);

		c.gridx=0;
		c.gridy=1;
		super.getPanel().add(labelEmail,c);
		

		c.gridx=1;	
		c.gridwidth=3;
		super.getPanel().add(email,c);
		
		c.gridwidth=1;
		
		c.gridx=0;
		c.gridy=2;
		super.getPanel().add(labelPassword,c);
		
		c.gridx=1;
		super.getPanel().add(password,c);

		c.gridx=2;
		c.anchor=c.EAST;
		super.getPanel().add(labelVerificarPassword,c);

		c.anchor=c.WEST;
		
		c.gridx=3;
		super.getPanel().add(verificarPassword,c);
		
		c.gridwidth=2;
		c.gridy=3;
		c.gridx=0;
		c.anchor=c.EAST;
		super.getPanel().add(botonRegistrarse,c);

		
		c.insets=new Insets(0, 50, 0, 0);
		c.gridwidth=1;
		c.gridx=2;
		c.anchor=c.WEST;
		super.getPanel().add(botonCancelar,c);
		


	}
	

	@Override
	public void pintar(Object o) {
		// TODO Auto-generated method stub
		
	}
}
