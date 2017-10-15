package dataStructures;

import java.util.Date;

public class Libro extends Contenido{
    
    private int pkLibro;
    private int numPag;
    private String capituloMuestra;

    public Libro(int pk, String titulo, String codigo, String imagen, Date fechaCreacion, int pkLibro, int numPag, String capituloMuestra) {
        super(pk, titulo, codigo, imagen, fechaCreacion);
        this.pkLibro = pkLibro;
        this.numPag = numPag;
        this.capituloMuestra = capituloMuestra;
    }
    public Libro(String titulo, String codigo, String imagen, Date fechaCreacion, int numPag, String capituloMuestra) {
        super(0, titulo, codigo, imagen, fechaCreacion);
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

}
