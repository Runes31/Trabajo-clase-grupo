package models;

import dataStructures.Actor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<Actor> getActores(int peliPk) throws SQLException {

        List<Actor> actores = new ArrayList<>();
        String sql = "SELECT act_pk,act_nombre "
                + "FROM act_actores,pelact_pelicula_actores "
                + "WHERE act_actores.act_pk = pelact_pelicula_actores.act_actores_act_pk "
                + "AND pel_pelicula_pel_pk = ?";

        PreparedStatement ps = con.getConn().prepareStatement(sql);
        ps.setInt(1, peliPk);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Actor actor = new Actor(rs.getString(2));
            actores.add(actor);

        }
        return actores;
    }
}
