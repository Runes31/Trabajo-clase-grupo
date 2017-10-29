package dataStructures;

import helpers.CapituloHelper;

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
    public Libro(Contenido contenido, int numPag, String capituloMuestra) {
        super(contenido.getTitulo(), contenido.getCodigo(), contenido.getImagen(), contenido.getStock());
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
        String rutaLocal = "capitulos_muestra/" + this.getCodigo() + "_" + capituloMuestra.substring(capituloMuestra.lastIndexOf("\\") + 1, capituloMuestra.length());

        File src = new File(capituloMuestra);
        File target = new File(rutaLocal);

        String rutaExiste = "capitulos_muestra/" + capituloMuestra.substring(capituloMuestra.lastIndexOf("\\")+1, capituloMuestra.length());
        if (new File(rutaExiste).exists()) {
            capituloMuestra = capituloMuestra.substring(capituloMuestra.lastIndexOf("\\")+1, capituloMuestra.length());
        } else {
            Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            capituloMuestra = this.getCodigo() + "_" + capituloMuestra.substring(capituloMuestra.lastIndexOf("\\") + 1, capituloMuestra.length());
        }
    }

    public void deleteCapitulo(){
        CapituloHelper.getCapituloMuestra(this).delete();
    }
}
