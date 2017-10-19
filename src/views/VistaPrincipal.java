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
		private String rutaPrincipal = "C:/Users/navin/workspace/Trabajo-clase-grupo/imagenes/";
		private ImageIcon imagenCast = new ImageIcon(rutaPrincipal + "logo.jpg");		
		private Image imagenLogo = imagenCast.getImage();
		//nombre app
		private static final String NOMBREAPLICACION= "Nombre Aplicacion";
		
		
		public String getRutaPrincipal() {
			return rutaPrincipal;
		}
		public void setRutaPrincipal(String rutaPrincipal) {
			this.rutaPrincipal = rutaPrincipal;
		}

		
		public void crearPanel(){
			setTitle(NOMBREAPLICACION);
			setIconImage(imagenLogo);
			add(panel);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		//getter
		public JPanel getPanel() {
			return panel;
		}
		
		public abstract void pintar(Object o);
		
}
