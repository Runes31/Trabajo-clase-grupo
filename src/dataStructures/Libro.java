package dataStructures;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;

public class Libro extends Contenido{
    
    private int pkLibro;
    private int numPag;
    private String capituloMuestra;

    public Libro(int pk, String titulo, String codigo, String imagen, Date fechaCreacion, int stock, boolean reservado, int pkLibro, int
            numPag, String capituloMuestra) {
        super(pk, titulo, codigo, imagen, fechaCreacion, stock, reservado);
        this.pkLibro = pkLibro;
        this.numPag = numPag;
        this.capituloMuestra = capituloMuestra;
    }
    public Libro(String titulo, String codigo, String imagen, int stock, boolean reservado, int numPag, String capituloMuestra) {
        super(0, titulo, codigo, imagen, stock, reservado);
        this.pkLibro = 0;
        this.numPag = numPag;
        this.capituloMuestra = capituloMuestra;
    }

    public int getPkLibro() {
        return pkLibro;
    }

    public int getNumPag() {
        return numPag;
    }

    public String getCapituloMuestra() {
        return capituloMuestra;
    }


    public void copyCapituloToLocal() throws IOException {
        long millis = System.currentTimeMillis();
        String rutaLocal = "imagenes/caratulas/"+ millis + capituloMuestra.substring(capituloMuestra.lastIndexOf("/"), capituloMuestra.length());

        File src = new File(capituloMuestra);
        File target = new File(rutaLocal);

        Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        capituloMuestra = millis + capituloMuestra.substring(capituloMuestra.lastIndexOf("/"), capituloMuestra.length());
    }
}
