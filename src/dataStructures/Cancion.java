/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

public class Cancion {
    private int pkCancion;
    private String nombre;

    public Cancion(int pkCancion, String nombre) {
        this.pkCancion = pkCancion;
        this.nombre = nombre;
    }

    public int getPkCancion() {
        return pkCancion;
    }

    public String getNombre() {
        return nombre;
    }
}
