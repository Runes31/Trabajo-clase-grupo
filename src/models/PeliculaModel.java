package models;

import dataStructures.Contenido;
import dataStructures.Director;
import dataStructures.Pelicula;
import dataStructures.Productora;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeliculaModel extends ContenidoModel {
    
    private ProductoraModel productora;
    private DirectorModel director;
    private ActoresModel actores;
    
    public PeliculaModel() throws SQLException, ClassNotFoundException {
        
        this.productora = new ProductoraModel();
        this.director = new DirectorModel();
        this.actores = new ActoresModel();
    }
    
    public void createPelicula(Pelicula pelicula) throws ModelException {
        
        try {
            con.getConn().setAutoCommit(false);
            
            int pkCont = insertContenido(pelicula);
            int pkProd = productora.insertProductora(pelicula.getProductora());
            int pkDir = director.insertDirector(pelicula.getDirector());
            
            insertPelicula(pkCont, pkProd, pkDir);
            
            con.getConn().commit();
            
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int insertPelicula(int conPk, int proPk, int dirPk) throws SQLException {
        
        String sql = "INSERT INTO pel_pelicula (con_contenido_con_pk,pro_productora_pro_pk,dir_directores_dir_pk) "
                + "VALUES (?,?,?)";
        
        PreparedStatement ps = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ps.setInt(1, conPk);
        ps.setInt(2, proPk);
        ps.setInt(3, dirPk);
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        
        rs.next();
        
        return rs.getInt(1);
    }
    
    public List<Contenido> getPeliculas() throws SQLException, ClassNotFoundException {
        
        List<Contenido> peliculas = new ArrayList<>();
        String sql = "SELECT pel_pk,con_contenido_pk,pro_productora_pro_pk,dir_directores_dir_pk,dir_nombre,act_nombre,"
                + "pro_nombre,con_pk,con_titulo,con_codigo,con_imagen,con_fecha_creacion,con_stock ,act_actores_act_pk,pel_pelicula_pel_pk,"
                + "FROM act_actores,pelact_pelicula_actores,dir_directores,pro_productora,pel_pelicula,con_contenido "
                + "WHERE pel_pelicula.pro_productora_pro_pk = pro_productora.pro_pk "
                + "AND pel_pelicula.con_contenido_con_pk = con_contenido.con_pk "
                + "AND pel_pelicula.dir_directores_dir_pk = dir_directores.dir_pk "
                + "AND act_actores.act_pk = pelact_pelicula_actores.act_actores_act_pk "
                + "AND pelact_pelicula_actores.pel_pelicula_pel_pk = pel_pelicula.pel_pk;";
        
        PreparedStatement st = con.getConn().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {
            
            PrestamosModel prestamosModel = new PrestamosModel();
            boolean prestado = prestamosModel.contenidoPrestado(rs.getInt(8));
            Productora p = new Productora(rs.getString(7));
            Director d = new Director(rs.getString(5));
            
            Pelicula pel = new Pelicula(rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getDate(12),
                    rs.getInt(13), prestado, rs.getInt(1), p, d, actores.getActores(rs.getInt(1)));
            peliculas.add(pel);
            
        }
        
        return peliculas;
    }
    
    public void updatePelicula(Pelicula musica) throws SQLException {
        
        String sql = "UPDATE pel_pelicula "
                + "SET con_contenido_pk = ?,pro_productora_pro_pk = ?,dir_directores_dir_pk = ?";
        
        PreparedStatement psPel = con.getConn().prepareStatement(sql);
        psPel.setInt(1, musica.getPk());
        psPel.setInt(2, musica.getProductora().getPk());
        psPel.setInt(3, musica.getDirector().getPk());
        
        psPel.executeUpdate();
        
    }
    
    public void deletePelicula(Pelicula pelicula) throws SQLException {
        
        String sqlPeli = "DELETE FROM pel_pelicula "
                + "WHERE pel_pk = ?";
        
        String sqlActores = "DELETE FROM pelact_pelicula_actores "
                + "WHERE pel_pelicula_pel_pk = ?";
        
        PreparedStatement psAct = con.getConn().prepareStatement(sqlActores);
        psAct.setInt(1, pelicula.getPkPelicula());
        psAct.executeUpdate();
        
        PreparedStatement psPel = con.getConn().prepareStatement(sqlPeli);
        psPel.setInt(1, pelicula.getPkPelicula());
        psPel.executeUpdate();
        
        deleteContenido(pelicula.getPk());
        
    }
    
}
