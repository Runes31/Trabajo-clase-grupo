package models;

import controllers.UserController;
import dataStructures.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrestamosModel {
    private ConnectDB con;

    public PrestamosModel() throws SQLException, ClassNotFoundException {
        con = new ConnectDB();
    }

    public boolean hayStock(Contenido contenido) throws SQLException {
        String sql = "SELECT count(*) FROM pres_prestamo WHERE con_contenido_con_pk = ?";
        PreparedStatement countPrestamos = con.getConn().prepareStatement(sql);
        countPrestamos.setInt(1, contenido.getPk());

        ResultSet rs = countPrestamos.executeQuery();
        rs.next();
        int numPrestamos = rs.getInt(1);

        sql = "SELECT con_stock FROM con_contenido WHERE con_pk = ?";
        PreparedStatement countStock = con.getConn().prepareStatement(sql);
        countStock.setInt(1, contenido.getPk());
        rs = countStock.executeQuery();
        rs.next();
        int numStock = rs.getInt(1);

        return numStock > numPrestamos;
    }

    public void hacerPrestamo(Contenido contenido) throws SQLException {
        String sql = "INSERT INTO pres_prestamo(con_contenido_con_pk, usu_usuarios_usu_pk, pres_fecha_prestamo)" +
                     "VALUES(?, ?, now())";

        int userPk = UserController.getCurrentUser().getPk();

        PreparedStatement prestamo = con.getConn().prepareStatement(sql);
        prestamo.setInt(1, contenido.getPk());
        prestamo.setInt(2, userPk);

        prestamo.executeUpdate();
    }

    public List<Contenido> getPeliculas() throws SQLException, ClassNotFoundException {
        List<Contenido> peliculas = new ArrayList<>();
        String sql = "SELECT pel_pk,con_contenido_pk,pro_productora_pro_pk,dir_directores_dir_pk,dir_nombre,act_nombre," +
                "pro_nombre,con_pk,con_titulo,con_codigo,con_imagen,con_fecha_creacion,con_stock ,act_actores_act_pk,pel_pelicula_pel_pk," +
                "FROM con_contenido con" +
                "LEFT JOIN pel_pelicula pel ON pel.con_contenido_con_pk = con.con_pk" +
                "LEFT JOIN dir_directores dir ON dir.dir_pk = pel.dir_directores_dir_pk" +
                "LEFT JOIN pro_productora pro ON pro.pro_pk = pel.pro_productora_pro_pk" +
                "LEFT JOIN pres_prestamo pres ON pres.con_contenido_con_pk = con.con_pk" +
                "WHERE pres.usu_usuarios_usu_pk = ?";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        st.setInt(1, UserController.getCurrentUser().getPk());
        ResultSet rs = st.executeQuery();

        ActoresModel actores = new ActoresModel();
        while (rs.next()) {
            PrestamosModel prestamosModel = new PrestamosModel();
            boolean prestado = prestamosModel.contenidoPrestado(rs.getInt(8));
            Productora p = new Productora(rs.getString(7));
            Director d = new Director(rs.getString(5));

            Pelicula pel = new Pelicula(rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getDate(12),
                    rs.getInt(13), prestado, rs.getInt(1), p, d, actores.getActores(rs.getInt(1)));
            peliculas.add(pel);
        }

        return peliculas;
    }

    public List<Contenido> getLibros() throws SQLException {
        List<Contenido> libros = new ArrayList<>();
        String sql = "SELECT lib_pk,con_contenido_con_pk,lib_numero_paginas,lib_capitulo_muestra,con_pk,con_titulo,con_codigo,con_imagen,con_fecha_creacion,con_stock"
                + " FROM lib_libro lib"
                + " LEFT JOIN con_contenido AS con ON con.con_pk = lib.con_contenido_con_pk" +
                  " LEFT JOIN pres_prestamo pres ON pres.con_contenido_con_pk = con.con_pk" +
                  " WHERE pres.usu_usuarios_usu_pk = ?";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        st.setInt(1, UserController.getCurrentUser().getPk());
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int pkLibro = rs.getInt(1);
            int numPag = rs.getInt(3);
            String capMuestra = rs.getString(4);

            int pkCont = rs.getInt(5);
            String contTitulo = rs.getString(6);
            String contCodigo = rs.getString(7);
            String contImg = rs.getString(8);
            Date contFecha = rs.getDate(9);
            int contStock = rs.getInt(10);

            Libro libro1 = new Libro(pkCont, contTitulo, contCodigo, contImg, contFecha, contStock, true, pkLibro, numPag, capMuestra);

            libros.add(libro1);
        }

        return libros;
    }

    public List<Contenido> getMusica() throws SQLException, ClassNotFoundException {
        List<Contenido> musicas = new ArrayList<>();
        List<Cancion> canciones = new ArrayList<>();
        PrestamosModel prestamosModel = new PrestamosModel();

        String sql = "SELECT mus_pk,con_contenido_con_pk,disc_discografica_disc_pk,con_pk,con_titulo,con_codigo,"
                + "con_imagen,con_fecha_creacion,con_stock,canc_pk,canc_nombre,canc_orden,mus_musica_mus_pk,disc_pk,disc_nombre "
                + "FROM mus_musica mus" +
                "LEFT JOIN con_contenido con ON mus.con_contenido_con_pk = con.con_pk" +
                "LEFT JOIN canc_canciones canc ON canc.mus_musica_mus_pk = mus.mus_pk" +
                "LEFT JOIN disc_discografica disc ON disc.disc_pk = mus.disc_discografica_disc_pk" +
                "LEFT JOIN pres_prestamo pres ON pres.con_contenido_con_pk = con.con_pk" +
                "WHERE usu_usuarios_usu_pk = ?";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        st.setInt(1, UserController.getCurrentUser().getPk());
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int musPk = rs.getInt(1);
            int contPk = rs.getInt(2);
            int discMusPk = rs.getInt(3);
            int conPk = rs.getInt(4);
            String contTitulo = rs.getString(5);
            String contCodigo = rs.getString(6);
            String contImg = rs.getString(7);
            java.sql.Date contDate = rs.getDate(8);
            int contStock = rs.getInt(9);
            int cancPk = rs.getInt(10);
            String cancNombre = rs.getString(11);
            int cancOrden = rs.getInt(12);
            int cancMusPk = rs.getInt(13);
            int discPk = rs.getInt(14);
            String discNom = rs.getString(15);

            Discografica discografica = new Discografica(discPk, discNom);

            Cancion cancion = new Cancion(cancPk, cancNombre, cancOrden);
            canciones.add(cancion);

            while (rs.next() && rs.getInt(1) == musPk) {
                cancPk = rs.getInt(10);
                cancNombre = rs.getString(11);
                cancOrden = rs.getInt(12);

                cancion = new Cancion(cancPk, cancNombre, cancOrden);
                canciones.add(cancion);
            }

            boolean prestado = prestamosModel.contenidoPrestado(contPk);
            Musica musica = new Musica(contPk, contTitulo, contCodigo, contImg, contDate, contStock, prestado, musPk, discografica, canciones);
            musicas.add(musica);
        }
        return musicas;
    }

    public int getNumPrestamos() throws SQLException {
        String sql = "SELECT count(*) FROM pres_prestamo WHERE usu_usuarios_usu_pk = ?";

        int userPk = UserController.getCurrentUser().getPk();

        PreparedStatement getNumPrestamos = con.getConn().prepareStatement(sql);
        getNumPrestamos.setInt(1, userPk);

        ResultSet rs = getNumPrestamos.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public boolean contenidoPrestado(int pkCont) throws SQLException {
        String sql = "SELECT * FROM pres_prestamo WHERE con_contenido_con_pk = ? AND usu_usuarios_usu_pk = ?";

        int userPk = UserController.getCurrentUser().getPk();

        PreparedStatement prestado = con.getConn().prepareStatement(sql);
        prestado.setInt(1, pkCont);
        prestado.setInt(2, userPk);

        ResultSet rs = prestado.executeQuery();
        return rs.next();
    }

    public void devolverPrestamo(Contenido contenido) throws SQLException {
        String sql = "DELETE FROM pres_prestamo WHERE con_contenido_con_pk = ? AND usu_usuarios_usu_pk = ?";

        int userPk = UserController.getCurrentUser().getPk();

        PreparedStatement devolver = con.getConn().prepareStatement(sql);
        devolver.setInt(1, contenido.getPk());
        devolver.setInt(2, userPk);
        devolver.executeUpdate();
    }
}
