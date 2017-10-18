package models;

import dataStructures.Contenido;
import dataStructures.Musica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContenidoModel {
    ConnectDB con;

    public ContenidoModel() throws SQLException, ClassNotFoundException {
        con = new ConnectDB();
    }

    int insertContenido(Contenido contenido) throws SQLException {
        String sql = "INSERT INTO con_contenido(con_titulo, con_codigo, con_imagen, con_fecha_creacion, con_stock)" +
                "VALUES(?,?,?,now(), ?)";

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
    
    public void getContenido(int pkFormato) throws SQLException{
        String sql = "SELECT con_titulo,con_codigo,con_imagen,con_fecha_creacion,con_stock"+ "FROM con_contenido"+"WHERE con_pk LIKE ?";
        
        PreparedStatement ps = con.getConn().prepareStatement(sql);
        ps.setInt(1, pkFormato);
        
        ResultSet rs = ps.executeQuery(sql);
        
        if(rs.next()){
            
            
            
        }
        
        
    }

    public List<Contenido> getNovedades() {
        return new ArrayList<>();
    }
}
