package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.MainController;
import controllers.UserController;
import dataStructures.User;

public class VistaRegistro extends VistaPrincipal {
	private GridBagLayout grid = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();

	private JLabel labelNombreUsuario = new JLabel("Alias: ");
	private JTextField nombreUsuario = new JTextField(10);
	private JLabel labelNombreCompleto = new JLabel("Nombre y Apellidos: ");
	private JTextField nombreCompleto = new JTextField(10);
	private JLabel labelContraseña = new JLabel("Contraseña: ");
	private JPasswordField contraseña = new JPasswordField(10);
	private JLabel labelVerificarContraseña = new JLabel("Verificar contraseña: ");
	private JPasswordField verificarContraseña = new JPasswordField(10);
	private JLabel labelEmail = new JLabel("Email: ");
	private JTextField email = new JTextField(33);
	private JButton botonRegistrarse = new JButton("Registrarse");
	private JButton botonCancelar = new JButton("Cancelar");


	public VistaRegistro() {
		super.setSize(550, 300);
		super.crearPanel();

		super.getPanelGeneral().setLayout(grid);
		c.weighty = 0.1;
		c.gridheight = 1;
		c.anchor = c.WEST;

		c.gridx = 0;
		c.gridy = 0;
		super.getPanelGeneral().add(labelNombreUsuario, c);

		c.gridx = 1;
		super.getPanelGeneral().add(nombreUsuario, c);

		c.anchor = c.EAST;
		c.insets = new Insets(0, 20, 0, 0);
		c.gridx = 2;
		super.getPanelGeneral().add(labelNombreCompleto, c);

		c.anchor = c.WEST;

		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 3;
		super.getPanelGeneral().add(nombreCompleto, c);

		c.gridx = 0;
		c.gridy = 1;
		super.getPanelGeneral().add(labelEmail, c);


		c.gridx = 1;
		c.gridwidth = 3;
		super.getPanelGeneral().add(email, c);

		c.gridwidth = 1;

		c.gridx = 0;
		c.gridy = 2;
		super.getPanelGeneral().add(labelContraseña, c);

		c.gridx = 1;
		super.getPanelGeneral().add(contraseña, c);

		c.gridx = 2;
		c.anchor = c.EAST;
		super.getPanelGeneral().add(labelVerificarContraseña, c);

		c.anchor = c.WEST;

		c.gridx = 3;
		super.getPanelGeneral().add(verificarContraseña, c);

		c.gridwidth = 2;
		c.gridy = 3;
		c.gridx = 0;
		c.anchor = c.EAST;
		super.getPanelGeneral().add(botonRegistrarse, c);

		botonRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserController uc = new UserController();
				User usuario = new User(obtenerNombreUsuario(), obtenerNombreCompleto(), obtenerEmail());
				uc.registrarUsuario(usuario, obtenerContraseña(), obtenerVerificacionContraseña());
			}
		});


		c.insets = new Insets(0, 50, 0, 0);
		c.gridwidth = 1;
		c.gridx = 2;
		c.anchor = c.WEST;
		super.getPanelGeneral().add(botonCancelar, c);

		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.setView(new VistaLogin());
			}
		});


	}

	// Metodos de obtencion de datos de campos de texto
	private String obtenerNombreUsuario() {
		String resultado;
		resultado = nombreUsuario.getText();
		return resultado;
	}


	private String obtenerNombreCompleto() {
		String resultado;
		resultado = nombreCompleto.getText();
		return resultado;
	}

	private String obtenerEmail() {
		String resultado;
		resultado = email.getText();
		return resultado;
	}

	public String obtenerContraseña() {
		char[] cadena = contraseña.getPassword();
		String resultado = new String(cadena);
		return resultado;
	}

	public String obtenerVerificacionContraseña() {
		char[] cadena = verificarContraseña.getPassword();
		String resultado = new String(cadena);
		return resultado;
	}
	//Fin de Metodos de obtencion de datos de campos de texto
}