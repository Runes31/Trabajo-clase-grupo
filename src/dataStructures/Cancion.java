/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

public class Cancion implements Comparable<Cancion> {
    private int pkCancion;
    private String nombre;
    private int orden;

    public Cancion(int pkCancion, String nombre, int orden) {
        this.pkCancion = pkCancion;
        this.nombre = nombre;
        this.orden = orden;
    }

    public Cancion(String nombre, int orden) {
        this.nombre = nombre;
        this.orden = orden;
    }

    public int getPkCancion() {
        return pkCancion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getOrden() {
        return orden;
    }

    @Override
    public int compareTo(Cancion other) {
        if(other.orden > orden)
            return -1;
        if (other.orden < orden)
            return 1;
        return 0;
    }
}
