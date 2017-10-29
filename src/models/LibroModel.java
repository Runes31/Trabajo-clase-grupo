package models;

import dataStructures.Contenido;
import dataStructures.Libro;
import helpers.Logger;
import helpers.TipoLog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            insertLibro(libro, contenidoId);

            con.getConn().commit();

        } catch (SQLException ex) {
            Logger.log("Exception in createLibro", TipoLog.ERROR);
            Logger.log(ex);
            ex.printStackTrace();
            try {
                con.getConn().rollback();
            } catch (SQLException e) {
                Logger.log(e);
                e.printStackTrace();
            }
            throw new ModelException(ex);
        } finally {
            try {
                con.getConn().setAutoCommit(true);
            } catch (SQLException e) {
                Logger.log(e);
                e.printStackTrace();
            }
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

        insertLibro.executeUpdate();
    }

    public List<Contenido> getLibros() throws SQLException, ClassNotFoundException {
        List<Contenido> libros = new ArrayList<>();
        String sql = "SELECT lib_pk,lib_numero_paginas,lib_capitulo_muestra,con_pk,con_titulo,con_codigo,con_imagen,con_fecha_creacion,con_stock"
                + " FROM lib_libro lib"
                + " LEFT JOIN con_contenido AS con ON con.con_pk = lib.con_contenido_con_pk";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        PrestamosModel prestamosModel = new PrestamosModel();

        while (rs.next()) {
            int pkLibro = rs.getInt(1);
            int numPag = rs.getInt(2);
            String capMuestra = rs.getString(3);

            int pkCont = rs.getInt(4);
            String contTitulo = rs.getString(5);
            String contCodigo = rs.getString(6);
            String contImg = rs.getString(7);
            Date contFecha = rs.getDate(8);
            int contStock = rs.getInt(9);

            boolean prestado = prestamosModel.contenidoPrestado(pkCont);

            Libro libro1 = new Libro(pkCont, contTitulo, contCodigo, contImg, contFecha, contStock, prestado, pkLibro, numPag, capMuestra);

            libros.add(libro1);
        }

        return libros;
    }

    public List<Contenido> getLibros(String titulo) throws SQLException, ClassNotFoundException {
        List<Contenido> libros = new ArrayList<>();
        String sql = "SELECT lib_pk,lib_numero_paginas,lib_capitulo_muestra,con_pk,con_titulo,con_codigo,con_imagen,con_fecha_creacion,con_stock"
                + " FROM lib_libro lib"
                + " JOIN con_contenido AS con ON con.con_pk = lib.con_contenido_con_pk" +
                  " WHERE con.con_titulo LIKE ?";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        st.setString(1, "%" + titulo + "%");
        ResultSet rs = st.executeQuery();

        PrestamosModel prestamosModel = new PrestamosModel();

        while (rs.next()) {
            int pkLibro = rs.getInt(1);
            int numPag = rs.getInt(2);
            String capMuestra = rs.getString(3);

            int pkCont = rs.getInt(4);
            String contTitulo = rs.getString(5);
            String contCodigo = rs.getString(6);
            String contImg = rs.getString(7);
            Date contFecha = rs.getDate(8);
            int contStock = rs.getInt(9);

            boolean prestado = prestamosModel.contenidoPrestado(pkCont);

            Libro libro1 = new Libro(pkCont, contTitulo, contCodigo, contImg, contFecha, contStock, prestado, pkLibro, numPag, capMuestra);

            libros.add(libro1);
        }

        return libros;
    }

    public void deleteLibro(Libro libro) throws SQLException, ClassNotFoundException {
        PrestamosModel prestamosModel = new PrestamosModel();
        prestamosModel.deletePrestamosContenido(libro);

        int pkLibro = libro.getPkLibro();
        int pkContenido = libro.getPk();

        String sqlLibro = "DELETE FROM lib_libro "
                + "WHERE lib_pk = ?";

        PreparedStatement stLibro = con.getConn().prepareStatement(sqlLibro);

        stLibro.setInt(1, pkLibro);

        stLibro.executeUpdate();

        deleteContenido(pkContenido);
    }

    public void updateLibro(Libro libro) throws SQLException {
        updateContenido(libro);

        int libPk = libro.getPkLibro();
        int libNumPag = libro.getNumPag();
        String libCapMuestra = libro.getCapituloMuestra();

        String sqlLibro = "UPDATE lib_libro "
                + "SET lib_numero_paginas = ?,lib_capitulo_muestra = ?"
                + " WHERE lib_pk = ?";

        PreparedStatement stLibro = con.getConn().prepareStatement(sqlLibro);

        //LIBRO
        stLibro.setInt(1, libNumPag);
        stLibro.setString(2, libCapMuestra);
        stLibro.setInt(3, libPk);

        stLibro.executeUpdate();
    }
}