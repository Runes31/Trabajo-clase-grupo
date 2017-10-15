/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Contenido {
    
    private int pk;
    private String titulo;
    private String codigo;
    private String imagen;
    private Date fechaCreacion;

    Contenido(int pk, String titulo, String codigo, String imagen, Date fechaCreacion) {
        this.pk = pk;
        this.titulo = titulo;
        this.codigo = codigo;
        this.imagen = imagen;
        this.fechaCreacion = fechaCreacion;
    }

    
    public int getPk() {
        return pk;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getImagen() {
        return imagen;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }
}
