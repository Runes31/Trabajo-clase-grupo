package models;

import dataStructures.Actor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ActoresModel {

    private ConnectDB con;

    public ActoresModel() {
        con = new ConnectDB();
    }
    
    int insertDirector(Actor actor) throws SQLException {
        String sql = "INSERT INTO act_actores (act_nombre) VALUES (?) "
                + "ON DUPLICATE KEY UPDATE act_pk=LAST_INSERT_ID(act_pk), act_nombre=?;";

        PreparedStatement ps = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, actor.getNombre());
        ps.setString(2, actor.getNombre());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);

    }
}
