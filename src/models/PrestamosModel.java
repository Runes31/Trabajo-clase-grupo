package models;

import dataStructures.Contenido;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void hacerPrestamo(Contenido contenido) {
    }
}
