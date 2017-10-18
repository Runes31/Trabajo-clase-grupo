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
    private DiscograficaModel discografiaModel;

    public MusicaModel() throws SQLException, ClassNotFoundException {
        cancionModel = new CancionModel();
        discografiaModel = new DiscograficaModel();
    }

    public void createMusica(Musica musica) throws ModelException {
        try {
            con.getConn().setAutoCommit(false);

            int contenidoId = insertContenido(musica);
            int discId = discografiaModel.insertDiscografica(musica.getDiscografica());
            int musicaId = insertMusica(contenidoId, discId);
            cancionModel.insertCanciones(musica.getCanciones(), musicaId);

            con.getConn().commit();
        } catch (SQLException e) {
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

        PreparedStatement insertMusica = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        insertMusica.setInt(1, contenidoId);
        insertMusica.setInt(2, discograficaId);
        insertMusica.executeUpdate();

        ResultSet rs = insertMusica.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public List<Contenido> getMusicas() throws SQLException {

        List<Contenido> musicas = new ArrayList<>();
        List<Cancion> canciones = new ArrayList<>();

        String sql = "SELECT mus_pk,con_contenido_con_pk,disc_discografica_disc_pk,con_pk,con_titulo,con_codigo,"
                + "con_imagen,con_fecha_creacion,con_stock,canc_pk,canc_nombre,canc_orden,mus_musica_mus_pk,disc_pk,disc_nombre "
                + "FROM mus_musica,con_contenido,canc_canciones,disc_discografica "
                + "WHERE con_contenido.con_pk = mus_musica.con_contenido_con_pk "
                + "AND disc_discografica.disc_pk = mus_musica.disc_discografica_disc_pk "
                + "AND mus_musica.mus_pk = canc_canciones.mus_musica_mus_pk";

        Statement st = con.getConn().prepareStatement(sql);
        ResultSet rs = st.executeQuery(sql);

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

            Musica musica = new Musica(contPk, contTitulo, contCodigo, contImg, contDate, contStock, musPk, discografica, canciones);
            musicas.add(musica);

        }

        return musicas;

    }

    public void updateMusica(Musica musica) throws SQLException {

        String sqlContenido = "UPDATE con_contenido "
                + "SET con_titulo = ?,con_codigo = ?,con_imagen = ?,con_fecha_creacion = ?,con_stock = ? "
                + "WHERE con_pk  = ?";

        String sqlDiscografica = "UPDATE disc_discografica "
                + "SET disc_nombre = ?"
                + "WHERE disk_pk = ?";
        
        
        //Traba Alex discograficas
        PreparedStatement psCont = con.getConn().prepareStatement(sqlContenido);
        PreparedStatement psDisc = con.getConn().prepareStatement(sqlDiscografica);

        //CONTENIDO DE MUSICA
        psCont.setString(1, musica.getTitulo());
        psCont.setString(2, musica.getCodigo());
        psCont.setString(3, musica.getImagen());
        psCont.setDate(4, (Date) musica.getFechaCreacion());
        psCont.setInt(5, musica.getStock());
        psCont.setInt(6, musica.getPk());

        //DISCOGRAFICA
        psDisc.setString(1, musica.getDiscografica().getNombre());
        psDisc.setInt(2, musica.getDiscografica().getPk());

        //CANCIONES
        for (Cancion cancion : musica.getCanciones()) {

            String sqlCanciones = "UPDATE canc_canciones "
                    + "SET canc_nombre = ?,canc_orden = ? "
                    + "WHERE mus_musica_mus_pk = ?";

            PreparedStatement psCanc = con.getConn().prepareStatement(sqlCanciones);

            psCanc.setString(1, cancion.getNombre());
            psCanc.setInt(2, cancion.getOrden());
            psCanc.setInt(3, musica.getPkMusica());

            psCanc.executeUpdate(sqlCanciones);
        }

    }

    public void deleteMusica() {

    }

}
