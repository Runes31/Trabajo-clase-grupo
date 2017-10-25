package models;

import dataStructures.Director;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DirectorModel {

    private ConnectDB con;

    public DirectorModel() {
        con = new ConnectDB();
    }

    int insertDirector(Director director) throws SQLException {
        String sql = "INSERT INTO dir_directores (disc_nombre) VALUES (?) "
                + "ON DUPLICATE KEY UPDATE dir_pk=LAST_INSERT_ID(dir_pk), dir_nombre=?;";

        PreparedStatement ps = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, director.getNombre());
        ps.setString(2, director.getNombre());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);

    }
}
