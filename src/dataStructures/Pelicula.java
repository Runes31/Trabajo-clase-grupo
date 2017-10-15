/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Pelicula extends Contenido {
    
    private int pkPelicula;
    private Productora productora;
    private Director director;


    public Pelicula(int pk, String titulo, String codigo, String imagen, Date fechaCreacion, int pkPelicula, Productora productora, Director director) {
        super(pk, titulo, codigo, imagen, fechaCreacion);
        this.pkPelicula = pkPelicula;
        this.productora = productora;
        this.director = director;
    }

    public Pelicula(String titulo, String codigo, String imagen, Date fechaCreacion, Productora productora, Director director) {
        super(0, titulo, codigo, imagen, fechaCreacion);
        this.pkPelicula = 0;
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
