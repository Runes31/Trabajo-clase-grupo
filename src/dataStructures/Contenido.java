/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Contenido implements Comparable<Contenido> {
    
    private int pk;
    private String titulo;
    private String codigo;
    private String imagen;
    private Date fechaCreacion;
    private int stock;
    private boolean resevado;

    Contenido(int pk, String titulo, String codigo, String imagen, Date fechaCreacion, int stock, boolean resevado) {
        this.pk = pk;
        this.titulo = titulo;
        this.codigo = codigo;
        this.imagen = imagen;
        this.fechaCreacion = fechaCreacion;
        this.stock = stock;
        this.resevado = resevado;
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

    public int getStock() {
        return stock;
    }

    public boolean isResevado(){
        return resevado;
    }

    public void copyImageToLocal() throws IOException {
        String rutaLocal = String.valueOf(getClass().getResource("imagenes/caratulas/"+imagen.substring(imagen.lastIndexOf("/"),
                imagen.length())
        ));

        File src = new File(imagen);
        File target = new File(rutaLocal);

        Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        imagen = rutaLocal;
    }

    @Override
    public int compareTo(Contenido other) {
        if(other.fechaCreacion.before(fechaCreacion)) {
            return -1;
        }
        if(fechaCreacion.before(other.fechaCreacion)){
            return 1;
        }
        return 0;
    }
}
