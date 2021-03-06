package models;

import dataStructures.Cancion;
import dataStructures.Contenido;
import dataStructures.Discografica;
import dataStructures.Musica;

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
            int contenidoId = insertContenido(musica);
            int discId = discograficaModel.insertDiscografica(musica.getDiscografica());
            int musicaId = insertMusica(contenidoId, discId);
            cancionModel.insertCanciones(musica.getCanciones(), musicaId);
        } catch (SQLException e) {
            throw new ModelException(e);
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

        String sql = "SELECT mus_pk,con_pk,con_titulo,con_codigo,"
                + "con_imagen,con_fecha_creacion,con_stock, disc_pk,disc_nombre "
                + "FROM mus_musica mus " +
                "LEFT JOIN con_contenido con ON mus.con_contenido_con_pk = con.con_pk " +
                "LEFT JOIN disc_discografica disc ON disc.disc_pk = mus.disc_discografica_disc_pk " +
                "LEFT JOIN pres_prestamo pres ON pres.con_contenido_con_pk = con.con_pk ";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int musPk = rs.getInt(1);
            int conPk = rs.getInt(2);
            String contTitulo = rs.getString(3);
            String contCodigo = rs.getString(4);
            String contImg = rs.getString(5);
            Date contDate = rs.getDate(6);
            int contStock = rs.getInt(7);
            int discPk = rs.getInt(8);
            String discNom = rs.getString(9);

            Discografica discografica = new Discografica(discPk, discNom);

            canciones = cancionModel.getCanciones(musPk);

            boolean prestado = prestamosModel.contenidoPrestado(conPk);
            Musica musica = new Musica(conPk, contTitulo, contCodigo, contImg, contDate, contStock, prestado, musPk, discografica, canciones);
            musicas.add(musica);
        }
        return musicas;
    }

    public List<Contenido> getMusica(String titulo) throws SQLException, ClassNotFoundException {

        List<Contenido> musicas = new ArrayList<>();
        List<Cancion> canciones = new ArrayList<>();
        PrestamosModel prestamosModel = new PrestamosModel();

        String sql = "SELECT mus_pk,con_pk,con_titulo,con_codigo," +
                "con_imagen,con_fecha_creacion,con_stock,canc_pk,canc_nombre,canc_orden,disc_pk,disc_nombre " +
                "FROM mus_musica mus " +
                "JOIN con_contenido con ON mus.con_contenido_con_pk = con.con_pk " +
                "LEFT JOIN canc_canciones canc ON canc.mus_musica_mus_pk = mus.mus_pk " +
                "LEFT JOIN disc_discografica disc ON disc.disc_pk = mus.disc_discografica_disc_pk " +
                "LEFT JOIN pres_prestamo pres ON pres.con_contenido_con_pk = con.con_pk " +
                "WHERE con.con_titulo LIKE ?";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        st.setString(1, "%" + titulo + "%");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int musPk = rs.getInt(1);
            int conPk = rs.getInt(2);
            String contTitulo = rs.getString(3);
            String contCodigo = rs.getString(4);
            String contImg = rs.getString(5);
            Date contDate = rs.getDate(6);
            int contStock = rs.getInt(7);
            int cancPk = rs.getInt(8);
            String cancNombre = rs.getString(9);
            int cancOrden = rs.getInt(10);
            int discPk = rs.getInt(11);
            String discNom = rs.getString(12);

            Discografica discografica = new Discografica(discPk, discNom);

            Cancion cancion = new Cancion(cancPk, cancNombre, cancOrden);
            canciones.add(cancion);

            while (rs.next() && rs.getInt(1) == musPk) {
                cancPk = rs.getInt(8);
                cancNombre = rs.getString(9);
                cancOrden = rs.getInt(10);

                cancion = new Cancion(cancPk, cancNombre, cancOrden);
                canciones.add(cancion);
            }

            boolean prestado = prestamosModel.contenidoPrestado(conPk);
            Musica musica = new Musica(conPk, contTitulo, contCodigo, contImg, contDate, contStock, prestado, musPk, discografica, canciones);
            musicas.add(musica);
        }
        return musicas;
    }

    public void updateMusica(Musica musica) throws SQLException {
        //CONTENIDO DE MUSICA
        updateContenido(musica);

        //DISCOGRAFICA
        int discPk = discograficaModel.insertDiscografica(musica.getDiscografica());
        String sqlMusica = "UPDATE mus_musica SET disc_discografica_disc_pk = ? WHERE mus_pk = ?";

        PreparedStatement updateMusica = con.getConn().prepareStatement(sqlMusica);
        updateMusica.setInt(1, discPk);
        updateMusica.setInt(2, musica.getPkMusica());

        updateMusica.executeUpdate();

        //CANCIONES
        cancionModel.updateCanciones(musica.getCanciones(), musica.getPkMusica());
    }

    public void deleteMusica(Musica m) throws SQLException, ClassNotFoundException {
        PrestamosModel prestamosModel = new PrestamosModel();
        prestamosModel.deletePrestamosContenido(m);

        int musPk = m.getPkMusica();
        cancionModel.deleteCanciones(musPk);

        String sql = "DELETE FROM mus_musica "
                + "WHERE mus_pk = ?";

        PreparedStatement ps = con.getConn().prepareStatement(sql);
        ps.setInt(1, musPk);
        ps.executeUpdate();

        int contPk = m.getPkContenido();
        deleteContenido(contPk);  
    }
}