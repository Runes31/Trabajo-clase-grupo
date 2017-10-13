package controllers;

public class Musica {
    
    private int pk;
    private Contenido contenido;
    private Discografica discografica;

    public Musica(int pk, Contenido contenido, Discografica discografica) {
        this.pk = pk;
        this.contenido = contenido;
        this.discografica = discografica;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public Discografica getDiscografica() {
        return discografica;
    }

    public void setDiscografica(Discografica discografica) {
        this.discografica = discografica;
    }
    
    
}
