package models;

import dataStructures.Contenido;
import dataStructures.Director;
import dataStructures.Pelicula;
import dataStructures.Productora;
import helpers.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            
            int pkPelicula = insertPelicula(pkCont, pkProd, pkDir);

            actores.insertActores(pelicula.getActores(), pkPelicula);
            
            con.getConn().commit();
            
        } catch (SQLException ex) {
            Logger.log(ex);
            ex.printStackTrace();
            try {
                con.getConn().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new ModelException(ex);
        } finally {
            try {
                con.getConn().setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        String sql = "SELECT pel_pk,pro_productora_pro_pk,pro_nombre,dir_directores_dir_pk,dir_nombre," +
                "con_pk,con_titulo,con_codigo,con_imagen,con_fecha_creacion,con_stock " +
                "FROM con_contenido con " +
                "LEFT JOIN pel_pelicula pel ON pel.con_contenido_con_pk = con.con_pk " +
                "LEFT JOIN dir_directores dir ON dir.dir_pk = pel.dir_directores_dir_pk " +
                "LEFT JOIN pro_productora pro ON pro.pro_pk = pel.pro_productora_pro_pk";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        PrestamosModel prestamosModel = new PrestamosModel();
        while (rs.next()) {
            boolean prestado = prestamosModel.contenidoPrestado(rs.getInt(6));
            Productora p = new Productora(rs.getInt(2),rs.getString(3));
            Director d = new Director(rs.getInt(4), rs.getString(5));

            Pelicula pel = new Pelicula(rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDate(10),
                    rs.getInt(11), prestado, rs.getInt(1), p, d, actores.getActores(rs.getInt(1)));
            peliculas.add(pel);
        }

        return peliculas;
    }

    public List<Contenido> getPeliculas(String titulo) throws SQLException, ClassNotFoundException {

        List<Contenido> peliculas = new ArrayList<>();
        String sql = "SELECT pel_pk,pro_productora_pro_pk,pro_nombre,dir_directores_dir_pk,dir_nombre," +
                "con_pk,con_titulo,con_codigo,con_imagen,con_fecha_creacion,con_stock " +
                "FROM con_contenido con " +
                "LEFT JOIN pel_pelicula pel ON pel.con_contenido_con_pk = con.con_pk " +
                "LEFT JOIN dir_directores dir ON dir.dir_pk = pel.dir_directores_dir_pk " +
                "LEFT JOIN pro_productora pro ON pro.pro_pk = pel.pro_productora_pro_pk " +
                "WHERE con.con_titulo LIKE ?";

        PreparedStatement st = con.getConn().prepareStatement(sql);
        st.setString(1, "%" + titulo + "%");
        ResultSet rs = st.executeQuery();

        PrestamosModel prestamosModel = new PrestamosModel();
        while (rs.next()) {
            boolean prestado = prestamosModel.contenidoPrestado(rs.getInt(6));
            Productora p = new Productora(rs.getInt(2),rs.getString(3));
            Director d = new Director(rs.getInt(4), rs.getString(5));

            Pelicula pel = new Pelicula(rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDate(10),
                    rs.getInt(11), prestado, rs.getInt(1), p, d, actores.getActores(rs.getInt(1)));
            peliculas.add(pel);
        }

        return peliculas;
    }
    
    public void updatePelicula(Pelicula pelicula) throws SQLException {
        String sql = "UPDATE pel_pelicula "
                + "SET pro_productora_pro_pk = ?,dir_directores_dir_pk = ?";
        
        PreparedStatement psPel = con.getConn().prepareStatement(sql);
        psPel.setInt(1, productora.insertProductora(pelicula.getProductora()));
        psPel.setInt(2, director.insertDirector(pelicula.getDirector()));

        actores.updateActores(pelicula);
        
        psPel.executeUpdate();
    }
    
    public void deletePelicula(Pelicula pelicula) throws SQLException, ClassNotFoundException {
        PrestamosModel prestamosModel = new PrestamosModel();
        prestamosModel.deletePrestamosContenido(pelicula);

        String sqlActores = "DELETE FROM pelact_pelicula_actores "
                + "WHERE pel_pelicula_pel_pk = ?";
        
        PreparedStatement psAct = con.getConn().prepareStatement(sqlActores);
        psAct.setInt(1, pelicula.getPkPelicula());
        psAct.executeUpdate();

        String sqlPeli = "DELETE FROM pel_pelicula "
                + "WHERE pel_pk = ?";

        PreparedStatement psPel = con.getConn().prepareStatement(sqlPeli);
        psPel.setInt(1, pelicula.getPkPelicula());
        psPel.executeUpdate();
        
        deleteContenido(pelicula.getPk());
    }
}