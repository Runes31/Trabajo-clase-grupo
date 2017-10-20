package models;

import dataStructures.Productora;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoraModel extends ContenidoModel {

    private ConnectDB con;

    ProductoraModel() {
        con = new ConnectDB();
    }

    int insertProductora(Productora productora) throws SQLException {

        String sql = "INSERT INTO pro_productoras(pro-nombre) "
                + "VALUES (?)";

        PreparedStatement ps = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, productora.getNombre());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }
    
  

}
