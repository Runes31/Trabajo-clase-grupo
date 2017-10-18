package views;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javafx.scene.control.ListView;

public class VistaInicio extends VistaPrincipal{
//	private ListView lista = new javax.swing.text.html.ListView(arg0); 
	
	private JButton boton = new JButton();
	private JLabel misPresamos = new JLabel("Mis préstamos");
	private String iconoBoton;
	private String nombreBoton;
	
	public VistaInicio (){
		configurarBoton(iconoBoton, nombreBoton);
	}
	
	public void configurarBoton(String icono, String nombre){
		ImageIcon iconoBoton = new ImageIcon(super.getRutaPrincipal() + nombre + ".jpg");		
		boton.setIcon(iconoBoton);
		nombreBoton = nombre;
	}

	@Override
	public void pintar(Object o) {
		// TODO Auto-generated method stub
		
	}
}
