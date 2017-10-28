package models;

import dataStructures.Cancion;
import dataStructures.Contenido;
import dataStructures.Discografica;
import dataStructures.Musica;
import helpers.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaModel extends ContenidoModel {

    private CancionModel cancionModel;
    private DiscograficaModel discograficaModel;

    public MusicaModel() throws SQLException, ClassNotFoundException {
        cancionModel = new CancionModel();
        discograficaModel = new DiscograficaModel();
    }

    public void createMusica(Musica musica) throws ModelException {
        try {
            con.getConn().setAutoCommit(false);

            int contenidoId = insertContenido(musica);
            int discId = discograficaModel.insertDiscografica(musica.getDiscografica());
            int musicaId = insertMusica(contenidoId, discId);
            cancionModel.insertCanciones(musica.getCanciones(), musicaId);

            con.getConn().commit();
        } catch (SQLException e) {
            Logger.log(e);
            try {
                e.printStackTrace();
                con.getConn().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new ModelException(e);
        } finally {
            try {
                con.getConn().setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private int insertMusica(int contenidoId, int discograficaId) throws SQLException {
        String sql = "INSERT INTO mus_musica(con_contenido_con_pk, disc_discografica_disc_pk)"
                + "VALUES(?,?)";

        PreparedStatement ps = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, contenidoId);
        ps.setInt(2, discograficaId);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
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
                "LEFT JOIN pres_prestamo pres ON pres.con_contenido_con_pk = con.con_pk";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int musPk = rs.getInt(1);
            int contPk = rs.getInt(2);
            int discMusPk = rs.getInt(3);
            int conPk = rs.getInt(4);
            String contTitulo = rs.getString(5);
            String contCodigo = rs.getString(6);
            String contImg = rs.getString(7);
            Date contDate = rs.getDate(8);
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

    public List<Contenido> getMusica(String titulo) throws SQLException, ClassNotFoundException {

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
                "WHERE con.con_titulo LIKE ?";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        st.setString(1, "%" + titulo + "%");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int musPk = rs.getInt(1);
            int contPk = rs.getInt(2);
            int discMusPk = rs.getInt(3);
            int conPk = rs.getInt(4);
            String contTitulo = rs.getString(5);
            String contCodigo = rs.getString(6);
            String contImg = rs.getString(7);
            Date contDate = rs.getDate(8);
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

    public void updateMusica(Musica musica) throws SQLException {
        //CONTENIDO DE MUSICA
        String sqlContenido = "UPDATE con_contenido "
                + "SET con_titulo = ?,con_codigo = ?,con_imagen = ?,con_fecha_creacion = ?,con_stock = ? "
                + "WHERE con_pk  = ?";
        PreparedStatement psCont = con.getConn().prepareStatement(sqlContenido);
        psCont.setString(1, musica.getTitulo());
        psCont.setString(2, musica.getCodigo());
        psCont.setString(3, musica.getImagen());
        psCont.setDate(4, (Date) musica.getFechaCreacion());
        psCont.setInt(5, musica.getStock());
        psCont.setInt(6, musica.getPk());

        //DISCOGRAFICA
        int discPk = discograficaModel.insertDiscografica(musica.getDiscografica());
        String sqlMusica = "UPDATE mus_musica SET disc_discografica_disc_pk = ? WHERE mus_pk = ?";
        PreparedStatement updateMusica = con.getConn().prepareStatement(sqlMusica);
        updateMusica.setInt(1, discPk);
        updateMusica.setInt(2, musica.getPk());
        updateMusica.executeUpdate();

        //CANCIONES
        for (Cancion cancion : musica.getCanciones()) {

            String sqlCanciones = "UPDATE canc_canciones "
                    + "SET canc_nombre = ?,canc_orden = ? "
                    + "WHERE mus_musica_mus_pk = ?";

            PreparedStatement psCanc = con.getConn().prepareStatement(sqlCanciones);

            psCanc.setString(1, cancion.getNombre());
            psCanc.setInt(2, cancion.getOrden());
            psCanc.setInt(3, musica.getPkMusica());

            psCanc.executeUpdate();
        }
    }

    public void deleteMusica(Musica m) throws SQLException, ClassNotFoundException {

        int musPk = m.getPkMusica();
        int contPk = m.getPk();

        String sql = "DELETE FROM mus_musica"
                + "WHERE mus_pk = ?";

        PreparedStatement ps = con.getConn().prepareStatement(sql);
        ps.setInt(1, musPk);

        cancionModel.deleteCanciones(musPk);
        ps.executeUpdate();
        deleteContenido(contPk);  
    }
}