package dataStructures;

public class Director {
    
    private int pk;
    private String nombre;

    public Director(String nombre) {
        this.pk = 0;
        this.nombre = nombre;
    }

    public Director(int pk, String nombre) {
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
