package models;

import dataStructures.Cancion;
import dataStructures.Discografica;
import dataStructures.Musica;

import java.sql.*;
import java.util.List;

public class MusicaModel extends ContenidoModel {
    private CancionModel cancionModel;
    private DiscografiaModel discografiaModel;

    public MusicaModel() throws SQLException, ClassNotFoundException {
        cancionModel = new CancionModel();
        discografiaModel = new DiscografiaModel();
    }

    public void createMusica(Musica musica) throws ModelException {
        try {
            con.getConn().setAutoCommit(false);

            int contenidoId = insertContenido(musica);
            int discId = discografiaModel.insertDiscografica(musica.getDiscografica());
            int musicaId = insertMusica(contenidoId, discId);
            cancionModel.insertCanciones(musica.getCanciones(), musicaId);

            con.getConn().commit();
        } catch (SQLException e){
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
        String sql = "INSERT INTO mus_musica(con_contenido_con_pk, disc_discografica_disc_pk)" +
                     "VALUES(?,?)";

        PreparedStatement insertMusica = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        insertMusica.setInt(1, contenidoId);
        insertMusica.setInt(2, discograficaId);
        insertMusica.executeUpdate();

        ResultSet rs = insertMusica.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }
}
