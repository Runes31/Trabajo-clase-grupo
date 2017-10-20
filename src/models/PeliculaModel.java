package models;

import dataStructures.Contenido;
import dataStructures.Pelicula;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaModel extends ContenidoModel {

    public PeliculaModel() throws SQLException, ClassNotFoundException {
    }

    public List<Contenido> getPeliculas() {
        return new ArrayList<>();
    }

    public void updatePelicula(Pelicula musica) {
    }

    public void deletePelicula(Pelicula pelicula) {
    }

    public void createPelicula(Pelicula pelicula) throws ModelException {
        throw new ModelException(new Exception());
    }
}
