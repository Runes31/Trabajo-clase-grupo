package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
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

import dataStructures.TipoContenido;
import dataStructures.Contenido;
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
		super.setSize(1080,525);
		super.crearPanel();
		
		super.setLocationRelativeTo(null);
		super.setResizable(false);

		super.getPanel().setLayout(gridAnidado);
		super.getPanel().setLayout(grid);
		
		
		
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		c.weightx=3;
		super.getPanel().add(new Label("0",Label.CENTER), c);
		
		c.gridx=1;
		c.gridy=0;
		super.getPanel().add(new Label("1",Label.CENTER), c);
		
		c.gridx=2;
		c.gridy=0;
//		super.getPanel().add(new Label("Mis préstamos",Label.CENTER), c);
		
		c.gridx=3;
		c.gridy=0;
		super.getPanel().add(new Label("3",Label.CENTER), c);
		
		c.gridx=4;
		c.gridy=0;
		super.getPanel().add(new Label("4",Label.CENTER), c);
		
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

	public void pintarContenido(Object o) {
		HashMap<TipoContenido,List<Contenido>> mapa = (HashMap) o;
		
		Iterator it = mapa.keySet().iterator();
		c.weightx = 3;
		int fila=0;
	    while (it.hasNext()) {
			c.gridx=2;
			c.gridy=fila;
			super.getPanel().add(new Label("Mis préstamos",Label.CENTER), c);
	    	List<Contenido> listaContenidos = mapa.get(it.next());
	    	pintarFila(listaContenidos,fila+1);
	    	fila+=2;
	    }
	}
	
	private void pintarFila(List<Contenido> listaContenidos, int fila) {
		for (int i = 0; i < 6; i++) {
			c.gridx=i;
			c.gridy=fila;
			pintarElemento(listaContenidos.get(0));
		}	
	}

    private void pintarElemento(Contenido contenido) {
        JLabel elemento = new JLabel("prueba", new ImageIcon(contenido.getImagen()), Label.CENTER);
        super.getPanel().add(elemento);
    }
}
