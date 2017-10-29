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
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Position;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.xml.internal.ws.api.server.Container;

import controllers.ContentController;
import controllers.UserController;
import dataStructures.TipoContenido;
import helpers.ImageHelper;
import dataStructures.Contenido;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderWidths;

public class VistaInicio extends VistaPrincipal{
	private JOptionPane mensajeError;
	
	private GridBagLayout grid1 = new GridBagLayout();
	private GridBagConstraints gbc1 = new GridBagConstraints();
	
	private GridBagLayout grid2 = new GridBagLayout();
	GridBagConstraints gbc2 = new GridBagConstraints();
	
	private JPanel panel1 = new JPanel(); 
	private JPanel panel2 = new JPanel();
	

	private JTextField buscador = new JTextField("Buscar");
	private JButton desconectar = new JButton("Desconectar");
	
	private JList<String> lista = new JList<String>();
	private final int TAMAÑOANCHOLISTA = 300;
	
	public VistaInicio (){
	    super.setSize(1000,600);
		super.crearPanel();
		GridBagLayout gridAuxiliar = new GridBagLayout();
		GridBagConstraints gbcAux = new GridBagConstraints();
		gridAuxiliar.columnWidths = new int[]{800 , 200};
		super.getPanel().setLayout(gridAuxiliar);
		//panel1
		panel1.setPreferredSize(new Dimension(800,600));
		grid1.columnWidths=new int[]{160,160,160,160,160};
		grid1.rowHeights=new int []{100,100,100,100,100,100};
		panel1.setLayout(grid1);
		gbcAux.gridx=0;
		gbcAux.gridy=0;
		super.getPanel().add(panel1,gbcAux);
		//panel2
		grid2.columnWidths=new int[]{196};
		grid2.rowHeights=new int []{50,470,50};
		panel2.setLayout(grid2);
		crearMenu();
		gbcAux.gridx=1;
		super.getPanel().add(panel2,gbcAux);		
	}
	
	
	public void crearMenu(){
	    gbc2.fill=GridBagConstraints.BOTH;
	    
//	    BUSCADOR
	    gbc2.gridx=0;
	    gbc2.gridy=0;

	    //Si presiona enter teniendo seleccionado el jtextfield realiza una busqueda
	    buscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    ContentController contentController = new ContentController();
                    contentController.buscarContenido(buscador.getText());
                }
            }
        });

	    //Placeholder text
	    buscador.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(buscador.getText().equals("Buscar")){
                    buscador.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (buscador.getText().equals("")){
                    buscador.setText("Buscar");
                }
            }
        });

	    panel2.add(buscador,gbc2);
	    
//	    LISTA
		String [] contenidoMenu = {"Listado completo", "Películas" ,"Libros", "Música","Mis prestamos"
				, "Películas","Libros","Música" };
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)lista.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER); 
		renderer.setVerticalAlignment(JLabel.BOTTOM);
		lista.setFont(new Font("serif",Font.ITALIC , 15));
		lista.setSelectedIndex(0);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setListData(contenidoMenu);
		lista.setFixedCellHeight(50);
		//Listener de la lista
		lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContentController contentController = new ContentController();

                switch (lista.getSelectedIndex()){
                    case 0: contentController.initHome(); break;
                    case 1: contentController.initHome(TipoContenido.PELICULA); break;
                    case 2: contentController.initHome(TipoContenido.LIBRO); break;
                    case 3: contentController.initHome(TipoContenido.MUSICA); break;
                    case 4: contentController.initHome(TipoContenido.PRESTAMO); break;
                    case 5: contentController.initHome(TipoContenido.PRESTAMO_PELICULA); break;
                    case 6: contentController.initHome(TipoContenido.PRESTAMO_LIBRO); break;
                    case 7: contentController.initHome(TipoContenido.PRESTAMO_MUSICA); break;
                }
            }
        });
		//FIN listener de la lista
		
		gbc2.gridy=1;
		panel2.add(lista,gbc2);
		
//		BOTON DESCONECTAR
		gbc2.gridy=2;
		panel2.add(desconectar,gbc2);
		desconectar.addMouseListener(new MouseListener() {

            
            @Override
            public void mouseClicked(MouseEvent e) {
                UserController userController = new UserController();
                userController.logout();
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
	}
	


	

	public void pintarContenido(Object o) {
		HashMap<TipoContenido, List<Contenido>> mapa= (HashMap<TipoContenido, List<Contenido>>) o;
		Iterator it = mapa.keySet().iterator();

		int numRows = mapa.size()*3;

		int[] rowHeight = new int[numRows];

		int height = 100;
		if(mapa.size() > 2)
		    height = 40;


		for (int i = 0; i<numRows; i++){
            rowHeight[i] = height;
        }

        grid1.rowHeights=rowHeight;
        panel1.setLayout(grid1);

		gbc1.weightx=1;
		gbc1.gridy = 0;
	    while (it.hasNext()) {
	        TipoContenido tipoContenido = (TipoContenido) it.next();
			gbc1.gridx=2;
			panel1.add(new Label(tipoContenido.getName(),Label.CENTER), gbc1);
            gbc1.gridy++;
			List<Contenido> listaContenidos = mapa.get(tipoContenido);
	    	pintarFila(listaContenidos);
            gbc1.gridy++;
            gbc1.gridx=2;
            pintarBotonVerMas(tipoContenido);
            gbc1.gridy++;
	    }
	}
	
	private void pintarFila(List<Contenido> listaContenidos) {
        gbc1.gridx = 0;
	    if(listaContenidos.isEmpty()){
	        gbc1.gridx=2;
	        JLabel elemento = new JLabel("No hay contenidos.");
	        panel1.add(elemento, gbc1);
	    } else {
            int maxFila = 5;

            if(listaContenidos.size() < maxFila)
                maxFila = listaContenidos.size();

            for (int i = 0; i < maxFila; i++) {
                pintarElemento(listaContenidos.get(i));
                gbc1.gridx++;
            }
        }
	}


    private void pintarElemento(Contenido contenido) {
		JLabel elemento = new JLabel();
        Image imagen = ImageHelper.getImagen(contenido.getImagen()).getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagen);
        elemento.setIcon(imagenFinal);
        panel1.add(elemento, gbc1);
    }


    private void pintarBotonVerMas(TipoContenido tipoContenido) {
	    JButton verMas = new JButton("Ver más");
        verMas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContentController contentController = new ContentController();
                contentController.initHome(tipoContenido);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel1.add(verMas, gbc1);
    }

    public void pintarContenido(List<Contenido> contenidoList){
	    gbc1.weightx=1;
	    gbc1.gridy = 0;

        grid1.rowHeights= new int[]{40,40,40,40,40,40,40,40,40};
        panel1.setLayout(grid1);

        if(contenidoList.isEmpty()){
            gbc1.gridx = 2;
            JLabel label = new JLabel("No hay contenidos.");
            panel1.add(label, gbc1);
        } else {
            int elemPorFila = 5;
            int maxFilas = 4;
            int i = 0;
            int x = 5;
            while (i < contenidoList.size() && i < elemPorFila * maxFilas) {
                if (i + 5 > contenidoList.size())
                    x = contenidoList.size() - i;

                pintarFila(contenidoList.subList(i, i + x));
                gbc1.gridy += 2;
                i += 5;
            }
        }
    }

}
