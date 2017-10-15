package dataStructures;

public class Actor {
    
    private int pk;
    private String nombre;

    public Actor(String nombre) {
        this.pk = 0;
        this.nombre = nombre;
    }

    public Actor(int pk, String nombre) {
        this.pk = pk;
        this.nombre = nombre;
    }

    public int getPk() {
        return pk;
    }

    public String getNombre() {
        return nombre;
    }
}
