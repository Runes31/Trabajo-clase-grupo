package models;

import dataStructures.Actor;
import dataStructures.Pelicula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class ActoresModel {

    private ConnectDB con;

    ActoresModel() throws SQLException, ClassNotFoundException {
        con = new ConnectDB();
    }

    int insertActor(Actor actor) throws SQLException {
        String sql = "INSERT INTO act_actores (act_nombre) VALUES (?) "
                + "ON DUPLICATE KEY UPDATE act_pk=LAST_INSERT_ID(act_pk), act_nombre=?";

        PreparedStatement ps = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, actor.getNombre());
        ps.setString(2, actor.getNombre());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);

    }

    List<Actor> getActores(int peliPk) throws SQLException {

        List<Actor> actores = new ArrayList<>();
        String sql = "SELECT act_pk,act_nombre "
                + "FROM act_actores,pelact_pelicula_actores "
                + "WHERE act_actores.act_pk = pelact_pelicula_actores.act_actores_act_pk "
                + "AND pel_pelicula_pel_pk = ?";

        PreparedStatement ps = con.getConn().prepareStatement(sql);
        ps.setInt(1, peliPk);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            actores.add(new Actor(rs.getInt(1) ,rs.getString(2)));
        }
        return actores;
    }

    void updateActores(Pelicula pelicula) throws SQLException {
        String sql = "DELETE FROM pelact_pelicula_actores WHERE pel_pelicula_pel_pk = ?";

        PreparedStatement deleteRelacion = con.getConn().prepareStatement(sql);
        deleteRelacion.setInt(1, pelicula.getPkPelicula());
        deleteRelacion.executeUpdate();

        sql = "INSERT INTO pelact_pelicula_actores(pel_pelicula_pel_pk, act_actores_act_pk) VALUES(?,?)";
        for (Actor a : pelicula.getActores()){
            PreparedStatement insertActores = con.getConn().prepareStatement(sql);
            insertActores.setInt(1, pelicula.getPkPelicula());
            insertActores.setInt(2, insertActor(a));

            insertActores.executeUpdate();
        }
    }

    void insertActores(List<Actor> actores, int pkPelicula) throws SQLException {
        String sql = "INSERT INTO pelact_pelicula_actores(pel_pelicula_pel_pk, act_actores_act_pk) VALUES(?,?)";
        for (Actor a : actores){
            PreparedStatement insertActores = con.getConn().prepareStatement(sql);
            insertActores.setInt(1, pkPelicula);
            insertActores.setInt(2, insertActor(a));

            insertActores.executeUpdate();
        }
    }
}
