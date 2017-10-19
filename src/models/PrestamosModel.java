package models;

import controllers.UserController;
import dataStructures.Contenido;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamosModel {
    private ConnectDB con;

    public boolean hayStock(Contenido contenido) throws SQLException {
        String sql = "SELECT count(*) FROM pres_prestamo WHERE con_contenido_con_pk = ?";
        PreparedStatement countPrestamos = con.getConn().prepareStatement(sql);
        countPrestamos.setInt(1, contenido.getPk());

        ResultSet rs = countPrestamos.executeQuery();
        rs.next();
        int numPrestamos = rs.getInt(1);

        sql = "SELECT con_stock FROM con_contenido WHERE con_pk = ?";
        PreparedStatement countStock = con.getConn().prepareStatement(sql);
        countStock.setInt(1, contenido.getPk());
        rs = countStock.executeQuery();
        rs.next();
        int numStock = rs.getInt(1);

        return numStock > numPrestamos;
    }

    public void hacerPrestamo(Contenido contenido) throws SQLException {
        String sql = "INSERT INTO pres_prestamo(con_contenido_con_pk, usu_usuarios_usu_pk, pres_fecha_prestamo)" +
                     "VALUES(?, ?, now())";

        int userPk = UserController.getCurrentUser().getPk();

        PreparedStatement prestamo = con.getConn().prepareStatement(sql);
        prestamo.setInt(1, contenido.getPk());
        prestamo.setInt(2, userPk);

        prestamo.executeUpdate();
    }

    public List<Contenido> getPrestamos(){
        String sql = "SELECT";

        return new ArrayList<>();
    }

    public List<Contenido> getPeliculas() {
        return new ArrayList<>();
    }
    public List<Contenido> getLibros() {
        return new ArrayList<>();
    }
    public List<Contenido> getMusica() {
        return new ArrayList<>();
    }

    public int getNumPrestamos() throws SQLException {
        String sql = "SELECT count(*) FROM pres_prestamo WHERE usu_usuarios_usu_pk = ?";

        int userPk = UserController.getCurrentUser().getPk();

        PreparedStatement getNumPrestamos = con.getConn().prepareStatement(sql);
        getNumPrestamos.setInt(1, userPk);

        ResultSet rs = getNumPrestamos.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    boolean contenidoPrestado(int pkCont) throws SQLException {
        String sql = "SELECT * FROM pres_prestamo WHERE con_contenido_con_pk = ? AND usu_usuarios_usu_pk = ?";

        int userPk = UserController.getCurrentUser().getPk();

        PreparedStatement prestado = con.getConn().prepareStatement(sql);
        prestado.setInt(1, pkCont);
        prestado.setInt(2, userPk);

        ResultSet rs = prestado.executeQuery();
        return rs.next();
    }
}
