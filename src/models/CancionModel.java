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

    public List<Cancion> getCanciones(int musicaId) throws SQLException {

        String sqlCanc = "SELECT canc_pk,canc_nombre,canc_orden,mus_musica_mus_pk "
                + "FROM canc_canciones"
                + "WHERE mus_musica_mus_pk = ?";

        List<Cancion> canciones = new ArrayList<>();

        PreparedStatement psCanc = con.getConn().prepareStatement(sqlCanc);
        psCanc.setInt(1, musicaId);

        ResultSet rs = psCanc.executeQuery();

        while (rs.next()) {

            Cancion cancion = new Cancion(rs.getInt(1), rs.getString(2), rs.getInt(3));
            canciones.add(cancion);
        }

        return canciones;

    }

    public Cancion getCancion(int cancionId) throws SQLException {

        String sqlCanc = "SELECT canc_pk,canc_nombre,canc_orden,mus_musica_mus_pk "
                + "FROM canc_canciones"
                + "WHERE canc_pk = ?";

        PreparedStatement psCanc = con.getConn().prepareStatement(sqlCanc);
        psCanc.setInt(1, cancionId);

        ResultSet rs = psCanc.executeQuery();

        Cancion cancion = new Cancion(rs.getInt(1), rs.getString(2), rs.getInt(3));

        return cancion;
    }

    public void deleteCanciones(int musicaId) throws SQLException {

        String sql = "DELETE * FROM canc_canciones WHERE mus_musica_mus_pk = ?";

        PreparedStatement psCanc = con.getConn().prepareStatement(sql);
        psCanc.setInt(1, musicaId);

        psCanc.executeUpdate();
    }

    public void deleteCancion(int cancionId) throws SQLException {

        String sql = "DELETE * FROM canc_canciones WHERE canc_pk = ?";

        PreparedStatement psCanc = con.getConn().prepareStatement(sql);
        psCanc.setInt(1, cancionId);

        psCanc.executeUpdate();

    }

    public void updateCancion(Cancion cancion) throws SQLException {

        String sql = "UPDATE canc_canciones SET canc_nombre = ?,canc_orden = ? WHERE canc_pk = ?";

        PreparedStatement psCanc = con.getConn().prepareStatement(sql);
        psCanc.setString(1, cancion.getNombre());
        psCanc.setInt(2, cancion.getOrden());
        psCanc.setInt(3, cancion.getPkCancion());

        psCanc.executeUpdate();

    }

    public void updateCanciones(List<Cancion> canciones, int musicaId) throws SQLException {

            deleteCanciones(musicaId);
            insertCanciones(canciones, musicaId);

    }
}
