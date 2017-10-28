package helpers;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class ImageHelper {

    public static Image getLogo() {
        
        File fl = new File("imagenes/logo.jpg");
        String filePath = fl.getAbsolutePath();

        ImageIcon imagenCast = new ImageIcon(filePath);
        Image imagenLogo = imagenCast.getImage();
        
        return imagenLogo;
    }
    
    public static Image getImagen(String nombreArchivo) {
        
        File fl = new File("imagenes/caratulas/"+nombreArchivo);
        String filePath = fl.getAbsolutePath();

        ImageIcon imagenCast = new ImageIcon(filePath);
        Image imagenLogo = imagenCast.getImage();
        
        return imagenLogo;
    }
    
}