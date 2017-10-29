package models;

import dataStructures.Contenido;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContenidoModel {

    ConnectDB con;

    public ContenidoModel() throws SQLException, ClassNotFoundException {
        con = new ConnectDB();
    }

    int insertContenido(Contenido contenido) throws SQLException {
        String sql = "INSERT INTO con_contenido(con_titulo, con_codigo, con_imagen, con_fecha_creacion, con_stock) "
                + "VALUES(?,?,?,now(), ?)";

        PreparedStatement insertContenido = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        insertContenido.setString(1, contenido.getTitulo());
        insertContenido.setString(2, contenido.getCodigo());
        insertContenido.setString(3, contenido.getImagen());
        insertContenido.setInt(4, contenido.getStock());
        insertContenido.executeUpdate();

        ResultSet rs = insertContenido.getGeneratedKeys();

        rs.next();

        return rs.getInt(1);
    }

    void deleteContenido(int pkCont) throws SQLException {
        String sql = "DELETE FROM con_contenido "
                + "WHERE con_pk = ?";

        PreparedStatement ps = con.getConn().prepareStatement(sql);
        ps.setInt(1, pkCont);

        ps.executeUpdate();

        sql = "DELETE FROM pres_prestamo WHERE con_contenido_con_pk = ?";

        PreparedStatement prestamo = con.getConn().prepareStatement(sql);
        prestamo.setInt(1, pkCont);
        prestamo.executeUpdate();
    }

    void updateContenido(Contenido contenido) throws SQLException {
        String sqlContenido = "UPDATE con_contenido "
                + "SET con_titulo = ?,con_codigo = ?,con_imagen = ?,con_stock = ?"
                + " WHERE con_pk = ?";

        PreparedStatement stCont = con.getConn().prepareStatement(sqlContenido);


        int contPk = contenido.getPk();
        String contTitulo = contenido.getTitulo();
        String contCodigo = contenido.getCodigo();
        String contImg = contenido.getImagen();

        int stock = contenido.getStock();

        stCont.setString(1, contTitulo);
        stCont.setString(2, contCodigo);
        stCont.setString(3, contImg);
        stCont.setInt(4, stock);
        stCont.setInt(5, contPk);

        stCont.executeUpdate();
    }
}
