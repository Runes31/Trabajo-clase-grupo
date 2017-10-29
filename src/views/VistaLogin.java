package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controllers.ContentController;
import controllers.MainController;
import controllers.UserController;

public class VistaLogin extends VistaPrincipal{
	//layout
	private GridBagLayout grid = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	
	private JLabel labelUsuario = new JLabel("Usuario");
	private JTextField usuario = new JTextField(10);
	private JLabel labelPassword = new JLabel("Contraseña");
	private JPasswordField password= new JPasswordField(10);
	private JButton botonLogin = new JButton("Login");
	private JButton botonRegistrarse = new JButton("Registrarse");
	private JOptionPane mensajeError = new JOptionPane();
	
	public VistaLogin(){
		super.setSize(400,300);
		super.crearPanel();
		super.getPanel().setLayout(grid);
		c.anchor=c.CENTER;
		c.gridx=0;
		c.gridy=0;
		super.getPanel().add(labelUsuario,c);

		c.insets=new Insets(10, 0, 0, 0);
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

        //Si presiona enter teniendo seleccionado el jtextfield intenta logearse
        usuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    UserController userController = new UserController();
                    userController.login(obtenerUsuario(), obtenerContraseña());
                }
            }
        });

        //Si presiona enter teniendo seleccionado el jtextfield intenta logearse
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    UserController userController = new UserController();
                    userController.login(obtenerUsuario(), obtenerContraseña());
                }
            }
        });
		
		botonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean comprobacion;
				UserController uc = new UserController();
				uc.login(obtenerUsuario(), obtenerContraseña());
				
			}
		});
		
		botonRegistrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainController.setView(new VistaRegistro());
				
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