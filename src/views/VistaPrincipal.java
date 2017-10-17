package views;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class VistaPrincipal extends JFrame{
		//panel
		private JPanel panel = new JPanel();
		//imagen	
		private String rutaImagen; 
		private ImageIcon imagenCast = new ImageIcon("C:/Users/navin/workspace/Trabajo-clase-grupo/imagenes/logo.jpg");		
		private Image imagenLogo = imagenCast.getImage();
		//nombre app
		private static final String NOMBREAPLICACION= "Nombre Aplicacion";
		
		public void crearPanel(){
			setSize(500,500);
			setTitle(NOMBREAPLICACION);
			setIconImage(imagenLogo);
			add(panel);
			setVisible(true);
		}
		//getter
		public JPanel getPanel() {
			return panel;
		}
		
		public abstract void pintar(Object o);
		
}
