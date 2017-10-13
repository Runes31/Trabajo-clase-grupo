/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Usuario
 */
public class Contenido {
    
    private int pk;
    private String titulo;
    private String codigo;
    private String imagen;

    public Contenido(int pk, String titulo, String codigo, String imagen) {
        this.pk = pk;
        this.titulo = titulo;
        this.codigo = codigo;
        this.imagen = imagen;
    }

    
    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
}
