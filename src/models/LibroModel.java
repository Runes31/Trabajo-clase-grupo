package models;

import dataStructures.Contenido;
import dataStructures.Libro;
import helpers.Logger;
import helpers.TipoLog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibroModel extends ContenidoModel {

    public LibroModel() throws SQLException, ClassNotFoundException {
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

    public List<Contenido> getLibros() throws SQLException {

        List<Contenido> libros = new ArrayList<>();
        String sql = "SELECT lib_pk,con_contenido_con_pk,lib_numero_paginas,lib_capitulo_muestra,con_pk,con_titulo,con_codigo,con_imagen,con_fecha_creacion,con_stock"
                + " FROM lib_libro lib,con_contenido"
                + " LEFT JOIN con_contenido AS con ON con.con_pk = lib.con_contenido_con_pk";

        Statement st = con.getConn().prepareStatement(sql);
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int pkLibro = rs.getInt(1);
            int pkContenido = rs.getInt(2);
            int numPag = rs.getInt(3);
            String capMuestra = rs.getString(4);

            int pkCont = rs.getInt(5);
            String contTitulo = rs.getString(6);
            String contCodigo = rs.getString(7);
            String contImg = rs.getString(8);
            Date contFecha = rs.getDate(9);
            int contStock = rs.getInt(10);

            Libro libro1 = new Libro(pkCont, contTitulo, contCodigo, contImg, contFecha, contStock, pkLibro, numPag, capMuestra);

            libros.add(libro1);
        }

        return libros;
    }

    public void deleteLibro(Libro libro) throws SQLException {

        int pkLibro = libro.getPkLibro();
        int pkContenido = libro.getPk();

        String sqlLibro = "DELETE FROM lib_libro "
                + "WHERE lib_pk = ?";

        String sqlContenido = "DELETE FROM con_contenido "
                + "WHERE con_pk = ?";

        PreparedStatement stLibro = con.getConn().prepareStatement(sqlLibro);
        PreparedStatement stCont = con.getConn().prepareStatement(sqlContenido);

        stLibro.setInt(1, pkLibro);
        stCont.setInt(1, pkContenido);

        stLibro.executeUpdate(sqlLibro);
        stCont.executeUpdate(sqlContenido);

    }

    public void updateLibro(Libro libro) throws SQLException {

        int contPk = libro.getPk();
        String contTitulo = libro.getTitulo();
        String contCodigo = libro.getCodigo();
        String contImg = libro.getImagen();
        Date libDate = libro.getFechaCreacion();

        int libStock = libro.getStock();
        int libPk = libro.getPkLibro();
        int libNumPag = libro.getNumPag();
        String libCapMuestra = libro.getCapituloMuestra();

        String sqlLibro = "UPDATE lib_libro "
                + "SET lib_numero_paginas = ?,lib_capitulo_muestra = ?"
                + " WHERE lib_pk = ?";

        String sqlContenido = "UPDATE con_contenido "
                + "SET con_titulo = ?,con_codigo = ?,con_imagen = ?,con_fecha_creacion = ?,con_stock = ?"
                + " WHERE con_pk = ?";

        PreparedStatement stLibro = con.getConn().prepareStatement(sqlLibro);
        PreparedStatement stCont = con.getConn().prepareStatement(sqlContenido);

        //LIBRO
        stLibro.setInt(1, libNumPag);
        stLibro.setString(2, libCapMuestra);
        stLibro.setInt(3, libPk);

        //CONTENIDO
        stCont.setString(1, contTitulo);
        stCont.setString(2, contCodigo);
        stCont.setString(3, contImg);
        stCont.setDate(4, (java.sql.Date) libDate);
        stCont.setInt(5, libStock);
        stCont.setInt(6, contPk);

        stLibro.executeUpdate(sqlLibro);
        stCont.executeUpdate(sqlContenido);

    }

}
