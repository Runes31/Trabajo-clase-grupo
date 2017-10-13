package dataStructures;

public class Discografica {
    
    private int pk;
    private String nombre;

    public Discografica(int pk, String nombre) {
        this.pk = pk;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPk() {
        return pk;
    }
}
