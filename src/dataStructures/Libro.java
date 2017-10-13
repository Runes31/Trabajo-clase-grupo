package dataStructures;

public class Libro extends Contenido{
    
    private int pkLibro;
    private int numPag;
    private String capituloMuestra;

    public Libro(int pk, String titulo, String codigo, String imagen, int pkLibro, int numPag, String capituloMuestra) {
        super(pk, titulo, codigo, imagen);
        this.pkLibro = pkLibro;
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
