package models;

import dataStructures.Cancion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionModel {

    private ConnectDB con;

    CancionModel() throws SQLException, ClassNotFoundException {
        con = new ConnectDB();
    }

    void insertCanciones(List<Cancion> canciones, int musicaId) throws SQLException {
        String sql = "INSERT INTO canc_canciones(canc_nombre, canc_orden, mus_musica_mus_pk)"
                + "VALUES";
        for (Cancion cancion : canciones) {
            sql += "(?, ?," + musicaId + "),";
        }

        sql = sql.substring(0, sql.length() - 1);

        PreparedStatement insertCanciones = con.getConn().prepareStatement(sql);

        int parameter = 1;
        for (Cancion cancion : canciones) {
            insertCanciones.setString(parameter++, cancion.getNombre());

            insertCanciones.setInt(parameter++, cancion.getOrden());
        }

        insertCanciones.executeUpdate();
    }

    void updateCanciones(List<Cancion> canciones, int musicaId) throws SQLException {
        deleteCanciones(musicaId);
        insertCanciones(canciones, musicaId);
    }

    void deleteCanciones(int musicaId) throws SQLException {

        String sql = "DELETE FROM canc_canciones WHERE mus_musica_mus_pk = ?";

        PreparedStatement psCanc = con.getConn().prepareStatement(sql);
        psCanc.setInt(1, musicaId);

        psCanc.executeUpdate();
    }
}