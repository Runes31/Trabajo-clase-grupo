package views;

import controllers.ContentController;
import controllers.MainController;
import controllers.UserController;
import dataStructures.*;
import helpers.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VistaInicio extends VistaPrincipal{
	private JOptionPane mensajeError;
	
	public VistaInicio (){
	    super.setSize(1000,600);
		super.crearPanel();
		crearMenu();
	}
	
	public void pintarContenido(LinkedHashMap<TipoContenido, List<Contenido>> mapa) {
		Iterator it = mapa.keySet().iterator();

		int numRows = mapa.size()*3;

		if (UserController.getCurrentUser().esAdmin()){
		    numRows += 2;
        }

		int[] rowHeight = new int[numRows];

		int height = 100;
		if(mapa.size() > 2)
		    height = 40;

		for (int i = 0; i<numRows; i++){
            rowHeight[i] = height;
        }

        grid1.rowHeights=rowHeight;
        panelContenido.setLayout(grid1);

		gbc1.weightx=1;
		gbc1.gridy = 0;

		//Mostrar solo si es admin y está en la home por defecto
        Map.Entry<TipoContenido, List<Contenido>> entry = mapa.entrySet().iterator().next();
        if (UserController.getCurrentUser().esAdmin() && entry.getKey() == TipoContenido.NOVEDADES){
		    JLabelWhite crear = new JLabelWhite("Crear");
		    gbc1.gridx = 2;
		    panelContenido.add(crear, gbc1);

		    gbc1.gridx = 1;
		    gbc1.gridy++;

            JButton musica = new JButton("Música");
            EstilosBotones.setCursor(musica);
            EstilosBotones.botonPrimary(musica);
            musica.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    MainController.setView(new VistaCrearMusica());
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
            panelContenido.add(musica, gbc1);
            gbc1.gridx++;

            JButton pelicula = new JButton("Pelicula");
            pelicula.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    MainController.setView(new VistaCrearPelicula());
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
            EstilosBotones.setCursor(pelicula);
            EstilosBotones.botonPrimary(pelicula);
            panelContenido.add(pelicula, gbc1);
            gbc1.gridx++;

            JButton libro = new JButton("Libro");
            libro.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    MainController.setView(new VistaCrearLibro());
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
            EstilosBotones.setCursor(libro);
            EstilosBotones.botonPrimary(libro);
            panelContenido.add(libro, gbc1);

            gbc1.gridy++;
        }

	    while (it.hasNext()) {
	        TipoContenido tipoContenido = (TipoContenido) it.next();
			gbc1.gridx=2;
			panelContenido.add(new JLabelWhite(tipoContenido.getName()), gbc1);
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
	        JLabelWhite elemento = new JLabelWhite("No hay contenidos.");
	        panelContenido.add(elemento, gbc1);
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
		EstilosBotones.setCursor(elemento);
        Image imagen = ImageHelper.getImagen(contenido.getImagen()).getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagen);
        elemento.setIcon(imagenFinal);
        EstilosBotones.removeHoverStyle(elemento);
        elemento.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(contenido instanceof Pelicula){
                    MainController.setView(new VistaPelicula((Pelicula) contenido));
                } else if(contenido instanceof Libro){
                    MainController.setView(new VistaLibro((Libro) contenido));
                } else {
                    MainController.setView(new VistaMusica((Musica) contenido));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                EstilosBotones.setHoverStyle(elemento);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                EstilosBotones.removeHoverStyle(elemento);
            }
        });
        panelContenido.add(elemento, gbc1);
    }


    private void pintarBotonVerMas(TipoContenido tipoContenido) {
	    JButton verMas = new JButton("Ver más");
	    EstilosBotones.setCursor(verMas);
	    EstilosBotones.botonPrimary(verMas);
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
        panelContenido.add(verMas, gbc1);
    }

    public void pintarContenido(List<Contenido> contenidoList){
	    gbc1.weightx=1;
	    gbc1.gridy = 0;

        grid1.rowHeights= new int[]{40,40,40,40,40,40,40,40,40};
        panelContenido.setLayout(grid1);

        if(contenidoList.isEmpty()){
            gbc1.gridx = 2;
            JLabelWhite label = new JLabelWhite("No hay contenidos.");
            panelContenido.add(label, gbc1);
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
