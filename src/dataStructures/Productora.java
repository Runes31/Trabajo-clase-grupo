package dataStructures;

public class Productora {
    
    private int pk;
    private String nombre;

    public Productora(String nombre) {
        this.pk = 0;
        this.nombre = nombre;
    }

    public Productora(int pk, String nombre) {
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
