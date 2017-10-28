package helpers;

import dataStructures.Libro;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CapituloHelper {
    public static void verCapituloMuestra(Libro libro) throws IOException {
        Desktop.getDesktop().open(getCapituloMuestra(libro));
    }

    private static File getCapituloMuestra(Libro libro) {
        return new File("capitulos_muestra/"+libro.getCapituloMuestra());
    }
}
