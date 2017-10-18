package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.scene.control.ListView;

public class VistaInicio extends VistaPrincipal{
	private JOptionPane mensajeError;
	private GridBagLayout grid = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	
	
	private JButton boton = new JButton();
	private JLabel misPresamos = new JLabel("Mis préstamos");
	private String iconoBoton;
	private String nombreBoton;
	private JTextField buscador = new JTextField("Buscador");
	private JButton desconectar = new JButton("Desconectar");
	
	private JList<String> menu = new JList<String>();
	
	public VistaInicio (){
		super.crearPanel();
		super.setSize(1080,760);
		super.setLocationRelativeTo(null);
		super.setResizable(false);

		super.getPanel().setLayout(grid);
		
		c.gridx=1;
		c.gridy=0;
		super.getPanel().add(buscador,c);
		
		c.gridx=1;
		c.gridy=1;
		super.getPanel().add(menu,c);
		
		c.gridx=1;
		c.gridy=2;
		super.getPanel().add(desconectar,c);
		
//		configurarBoton(iconoBoton, nombreBoton);
		
	}
	
//	public void configurarBoton(String icono, String nombre){
//		ImageIcon iconoBoton = new ImageIcon(super.getRutaPrincipal() + nombre + ".jpg");		
//		boton.setIcon(iconoBoton);
//		nombreBoton = nombre;
//	}

	@Override
	public void pintar(Object o) {
		String mensaje = (String) o;
		mensajeError.showMessageDialog(this , mensaje ,"Error",JOptionPane.ERROR_MESSAGE);
	}
	
//	@Override
//	public void pintarEnFilas(Object o) {
//		HashMap mapa = (HashMap) o;
//		
//		
//	}
}
