package dataStructures;

import java.util.List;

public class Musica extends Contenido {
    
    private int pkMusica;
    private Discografica discografica;
    private List<Cancion> canciones;

    public Musica(int pk, String titulo, String codigo, String imagen, int pkMusica, Discografica discografica, List<Cancion> canciones) {
        super(pk, titulo, codigo, imagen);
        this.pkMusica = pkMusica;
        this.discografica = discografica;
        this.canciones = canciones;
    }

    public int getPkMusica() {
        return pkMusica;
    }

    public Discografica getDiscografica() {
        return discografica;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }
}
