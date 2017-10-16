package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VistaRegistro extends VistaPrincipal{
	private GridBagLayout grid = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	
	private JLabel labelNombreCompleto = new JLabel("Nombre y Apellidos: ");
	private JTextField nombre = new JTextField(10);
	private JLabel labelPassword = new JLabel("Contraseña: ");
	private JPasswordField password= new JPasswordField(10);
	private JLabel labelVerificarPassword = new JLabel("Verificar contraseña: ");
	private JPasswordField comprobarPassword = new JPasswordField(10);
	private JLabel labelEmail = new JLabel("Email: ");
	private JTextField email = new JTextField(10);
	
	
	public VistaRegistro(){
		super.crearPanel();
		super.setSize(600,400);
		super.setLocationRelativeTo(null);
		super.getPanel().setLayout(grid);
		c.weighty=0.1;
		c.gridheight=1;
	}
}
