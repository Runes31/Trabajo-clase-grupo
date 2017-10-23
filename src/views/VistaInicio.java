package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.Position;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.xml.internal.ws.api.server.Container;

import javafx.scene.control.ListView;
import javafx.scene.layout.BorderWidths;

public class VistaInicio extends VistaPrincipal{
	private JOptionPane mensajeError;
	private GridBagLayout grid = new GridBagLayout();
	private GridBagLayout gridAnidado = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	
	
	private JButton boton = new JButton();
	private JLabel misPresamos = new JLabel("Mis préstamos");
	private String iconoBoton;
	private String nombreBoton;
	private JTextField buscador = new JTextField("Buscador");
	private JButton desconectar = new JButton("Desconectar");
	private JPanel panel1 = new JPanel();
	
	
	private JList<String> lista = new JList<String>();

	
	public VistaInicio (){
		super.setSize(1080,760);
		super.crearPanel();
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.getPanel().setLayout(gridAnidado);
		super.getPanel().setLayout(grid);
		
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		c.weightx=3;
		super.getPanel().add(new Label("Prueba"), c);
		
		c.gridx=1;
		c.gridy=0;
		super.getPanel().add(new Label("Prueba"), c);
		
		c.gridx=2;
		c.gridy=0;
		super.getPanel().add(new Label("Prueba"), c);
		
		c.gridx=3;
		c.gridy=0;
		super.getPanel().add(new Label("Prueba"), c);
		
		buscador.setPreferredSize(new Dimension(150, 50));
		c.gridx=5;
		c.gridy=0;
		c.gridwidth=2;
		super.getPanel().add(buscador,c);
		
		c.gridwidth=1;
		
		lista.setPreferredSize(new Dimension(150, 400));
		c.gridx=6;
		c.gridy=2;
		crearMenu();
		super.getPanel().add(lista,c);
		
		desconectar.setPreferredSize(new Dimension(150, 35));
		c.gridx=6;
		c.gridy=3;
		super.getPanel().add(desconectar,c);
		
//		configurarBoton(iconoBoton, nombreBoton);
		
	}
	
	public void crearMenu(){
		String [] contenidoMenu = {"Listado completo", "Películas" ,"Libros", "Música","Mis prestamos"
				, "Películas","Libros","Música" };
		lista.setFont(new Font("serif",Font.ITALIC , 15));
		lista.setSelectedIndex(0);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setListData(contenidoMenu);
		lista.setFixedCellHeight(50);
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
