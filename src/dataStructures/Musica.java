package dataStructures;

import java.util.Date;
import java.util.List;

public class Musica extends Contenido {
    
    private int pkMusica;
    private Discografica discografica;
    private List<Cancion> canciones;

    public Musica(int pk, String titulo, String codigo, String imagen, Date fechaCreacion, int stock, boolean reservado, int pkMusica,
                  Discografica discografica, List<Cancion> canciones) {
        super(pk, titulo, codigo, imagen, fechaCreacion, stock, reservado);
        this.pkMusica = pkMusica;
        this.discografica = discografica;
        this.canciones = canciones;
    }

    public Musica(Contenido contenido, Discografica discografica, List<Cancion> canciones) {
        super(contenido.getTitulo(), contenido.getCodigo(), contenido.getImagen(), contenido.getStock());
        this.pkMusica = 0;
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
