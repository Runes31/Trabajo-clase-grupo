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

    private static File getCapituloMuestra(Libro libro) {
        return new File("capitulos_muestra/"+libro.getCapituloMuestra());
    }
}
