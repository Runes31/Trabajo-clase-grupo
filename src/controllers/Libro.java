package controllers;

public class Libro extends Contenido{
    
    private int pkLibro;
    private int numPag;
    private String capituloMuestra;

    public Libro(int pk, int numPag, String capituloMuestra, int pkLibro, String titulo, String codigo, String imagen) {
        super(pk, titulo, codigo, imagen);
        this.pkLibro = pkLibro;
        this.numPag = numPag;
        this.capituloMuestra = capituloMuestra;
    }

    public int getPkLibro() {
        return pkLibro;
    }

    public void setPkLibro(int pkLibro) {
        this.pkLibro = pkLibro;
    }

    public int getNumPag() {
        return numPag;
    }

    public void setNumPag(int numPag) {
        this.numPag = numPag;
    }

    public String getCapituloMuestra() {
        return capituloMuestra;
    }

    public void setCapituloMuestra(String capituloMuestra) {
        this.capituloMuestra = capituloMuestra;
    }
    
    
    
}
