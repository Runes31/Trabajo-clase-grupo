package dataStructures;

public class Director {
    
    private int pk;
    private String nombre;

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
