package models;

import dataStructures.Discografica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiscograficaModel {

    private ConnectDB con;

    DiscograficaModel() throws SQLException, ClassNotFoundException {
        con = new ConnectDB();
    }

    int insertDiscografica(Discografica discografica) throws SQLException {
        String sql = "INSERT INTO disc_discografica (disc_nombre) VALUES (?)"
                + "ON DUPLICATE KEY UPDATE disc_pk=LAST_INSERT_ID(disc_pk), disc_nombre=?;";

        PreparedStatement insertDiscografica = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        insertDiscografica.setString(1, discografica.getNombre());
        insertDiscografica.setString(2, discografica.getNombre());

        insertDiscografica.executeUpdate();

        ResultSet rs = insertDiscografica.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Discografica getDiscografica(String nombre) throws SQLException {

        String sql = "SELECT disc_pk,disc_nombre FROM disc_discografica WHERE disc_nombre LIKE ?";

        PreparedStatement psDisc = con.getConn().prepareStatement(sql);
        psDisc.setString(1, nombre);

        ResultSet rs = psDisc.executeQuery(sql);

        Discografica disc = new Discografica(rs.getInt(1), rs.getString(2));

        return disc;

    }

    public List<Discografica> getDiscograficas() throws SQLException {

        List<Discografica> discograficas = new ArrayList<>();
        String sql = "SELECT disc_pk,disc_nombre FROM disc_discografica";
        PreparedStatement psDisc = con.getConn().prepareStatement(sql);

        ResultSet rs = psDisc.executeQuery(sql);

        while (rs.next()) {

            Discografica disc = new Discografica(rs.getInt(1), rs.getString(2));
            discograficas.add(disc);

        }

        return discograficas;
    }

}

