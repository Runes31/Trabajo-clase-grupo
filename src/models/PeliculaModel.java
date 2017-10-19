package models;

import dataStructures.Contenido;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaModel extends ContenidoModel {

    public PeliculaModel() throws SQLException, ClassNotFoundException {
    }

    public List<Contenido> getPeliculas() {
        return new ArrayList<>();
    }
}
