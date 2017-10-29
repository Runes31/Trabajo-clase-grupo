package models;

import dataStructures.Discografica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DiscograficaModel {

    private ConnectDB con;

    DiscograficaModel() throws SQLException, ClassNotFoundException {
        con = new ConnectDB();
    }

    int insertDiscografica(Discografica discografica) throws SQLException {
        String sql = "INSERT INTO disc_discografica (disc_nombre) VALUES (?) "
                + "ON DUPLICATE KEY UPDATE disc_pk=LAST_INSERT_ID(disc_pk), disc_nombre=?";

        PreparedStatement insertDiscografica = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        insertDiscografica.setString(1, discografica.getNombre());
        insertDiscografica.setString(2, discografica.getNombre());

        insertDiscografica.executeUpdate();

        ResultSet rs = insertDiscografica.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
}