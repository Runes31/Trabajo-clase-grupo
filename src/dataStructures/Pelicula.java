/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Pelicula extends Contenido {
    
    private int pkPelicula;
    private Productora productora;
    private Director director;
    private List<Actor> actores;


    public Pelicula(int pk, String titulo, String codigo, String imagen, Date fechaCreacion, int stock, boolean reservado, int
            pkPelicula, Productora productora, Director director, List<Actor> actores) {
        super(pk, titulo, codigo, imagen, fechaCreacion, stock, reservado);
        this.pkPelicula = pkPelicula;
        this.productora = productora;
        this.director = director;
        this.actores = actores;
    }

    public Pelicula(String titulo, String codigo, String imagen, int stock, boolean reservado, Productora productora,
                    Director director, List<Actor> actores) {
        super(0, titulo, codigo, imagen, stock, reservado);
        this.pkPelicula = 0;
        this.productora = productora;
        this.director = director;
        this.actores = actores;
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

    public List<Actor> getActores() {
        return actores;
    }
}
