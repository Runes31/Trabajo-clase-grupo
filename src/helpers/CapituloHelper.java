package helpers;

import dataStructures.Libro;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CapituloHelper {

    /**
     * Abre el archivo que guarda el capitulo de muestra con el programa por defecto
     * @param libro
     * @throws IOException
     */
    public static void verCapituloMuestra(Libro libro) throws IOException {
        Desktop.getDesktop().open(getCapituloMuestra(libro));
    }

    public static File getCapituloMuestra(Libro libro) {
        return new File("capitulos_muestra/"+libro.getCapituloMuestra());
    }

    public static String getAbsolutePath(Libro libro) {
        File f = new File("capitulos_muestra/"+libro.getCapituloMuestra());
        if(f.exists()){
            return f.getAbsolutePath();
        }
        return "";
    }
}
