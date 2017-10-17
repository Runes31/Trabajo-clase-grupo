package models;

import dataStructures.Libro;
import helpers.Logger;
import helpers.TipoLog;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LibroModel extends ContenidoModel {

    private Libro libro;

    public LibroModel(Libro libro) throws SQLException, ClassNotFoundException {
        this.libro = libro;
    }

    public void createLibro(Libro libro) throws ModelException {

        try {
            con.getConn().setAutoCommit(false);

            int contenidoId = insertContenido(libro);

            insertContenido(libro);
            insertLibro(libro, contenidoId);

            con.getConn().commit();

        } catch (SQLException ex) {
            Logger.log("Exception in createLibro", TipoLog.ERROR);
            Logger.log(ex);
        }

    }

    private void insertLibro(Libro libro, int contenidoPk) throws SQLException {

        String sql = "INSERT INTO lib_libro (con_contenido_con_pk,lib_numero_paginas,lib_capitulo_muestra)" + "VALUES(?,?,?)";

        int contenidoId = contenidoPk;
        int numPag = libro.getNumPag();
        String capMuestra = libro.getCapituloMuestra();

        PreparedStatement insertLibro = con.getConn().prepareStatement(sql);
        insertLibro.setInt(1, contenidoId);
        insertLibro.setInt(2, numPag);
        insertLibro.setString(3, capMuestra);

    }
    
    public List<Libro> getLibros(){
        String sql = "SELECT lib_numero_paginas FROM lib_libro";
        return null;
    }
    
    public void getLibro(){
        
    }

}
