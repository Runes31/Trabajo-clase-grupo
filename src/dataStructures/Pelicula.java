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

    public Pelicula(Contenido contenido, Productora productora,
                    Director director, List<Actor> actores) {
        super(contenido.getTitulo(), contenido.getCodigo(), contenido.getImagen(), contenido.getStock());
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
