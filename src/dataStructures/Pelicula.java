/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

/**
 *
 * @author Usuario
 */
public class Pelicula extends Contenido {
    
    private int pkPelicula;
    private Productora productora;
    private Director director;


    public Pelicula(int pk, String titulo, String codigo, String imagen, int pkPelicula, Productora productora, Director director) {
        super(pk, titulo, codigo, imagen);
        this.pkPelicula = pkPelicula;
        this.productora = productora;
        this.director = director;
    }

    public int getPkPelicula(){
        return pkPelicula;
    }

    public Productora getProductora() {
        return productora;
    }

    public Director getDirector() {
        return director;
    }
}
