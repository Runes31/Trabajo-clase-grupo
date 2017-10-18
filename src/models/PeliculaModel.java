package models;

import dataStructures.Contenido;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaModel {
    private ConnectDB con;

    public PeliculaModel() throws SQLException, ClassNotFoundException {
        con = new ConnectDB();
    }

    public List<Contenido> getPeliculas() {
        return new ArrayList<>();
    }
}
