package controllers;

public class Productora {
    
    private int pk;
    private String nombre;

    public Productora(int pk, String nombre) {
        this.pk = pk;
        this.nombre = nombre;
    }

    
    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
