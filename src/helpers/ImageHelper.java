package helpers;

import dataStructures.Contenido;

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
        Image imagenLogo;
        if (fl.exists() && !fl.isDirectory()) {
            String filePath = fl.getAbsolutePath();

            ImageIcon imagenCast = new ImageIcon(filePath);
            imagenLogo = imagenCast.getImage();
        } else {
            fl = new File("imagenes/caratulas/default.jpg");
            String filePath = fl.getAbsolutePath();

            ImageIcon imagenCast = new ImageIcon(filePath);
            imagenLogo = imagenCast.getImage();
        }
        
        return imagenLogo;
    }

    public static String getAbsolutePath(Contenido contenido){
        if(contenido.getImagen().isEmpty()){
            return "";
        }
        File fl = new File("imagenes/caratulas/"+contenido.getImagen());
        return fl.getAbsolutePath();
    }
}