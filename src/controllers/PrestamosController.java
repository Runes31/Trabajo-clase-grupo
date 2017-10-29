package controllers;

import dataStructures.Contenido;
import dataStructures.Libro;
import dataStructures.Musica;
import dataStructures.Pelicula;
import helpers.Logger;
import models.PrestamosModel;
import views.VistaLibro;
import views.VistaMusica;
import views.VistaPelicula;

import java.sql.SQLException;

public class PrestamosController {
    private static final int MAX_NUM_PRESTAMOS = 10;

    public void hacerPrestamo(Contenido contenido) {
        try {
            PrestamosModel prestamosModel = new PrestamosModel();
            if (UserController.getCurrentUser().esAdmin()){
                MainController.printToView("Los administradores no pueden realizar prestamos.");
            } else if (!prestamosModel.hayStock(contenido)) {
                MainController.printToView("No hay stock.");
            } else if(prestamosModel.getNumPrestamos() >= MAX_NUM_PRESTAMOS) {
                MainController.printToView("No puedes realizar más de " + MAX_NUM_PRESTAMOS + " prestamos a la vez.");
            } else if(prestamosModel.contenidoPrestado(contenido.getPk())){
                MainController.printToView("Ya tiene ese contenido prestado.");
            } else {
                prestamosModel.hacerPrestamo(contenido);
                if(contenido instanceof Pelicula){
                    Pelicula pel = new Pelicula(contenido.getPk(), contenido.getTitulo(), contenido.getCodigo(),
                            contenido.getImagen(), contenido.getFechaCreacion(), contenido.getStock(), true,
                            ((Pelicula) contenido).getPkPelicula(), ((Pelicula) contenido).getProductora(),
                            ((Pelicula) contenido).getDirector(), ((Pelicula) contenido).getActores());
                    MainController.setView(new VistaPelicula(pel));
                } else if(contenido instanceof Libro){
                    Libro lib = new Libro(contenido.getPk(), contenido.getTitulo(), contenido.getCodigo(), contenido.getImagen(),
                    contenido.getFechaCreacion(), contenido.getStock(), true, ((Libro) contenido).getPkLibro(),
                            ((Libro) contenido).getNumPag(), ((Libro) contenido).getCapituloMuestra());
                    MainController.setView(new VistaLibro(lib));
                } else {
                    Musica mus = new Musica(contenido.getPk(), contenido.getTitulo(), contenido.getCodigo(), contenido.getImagen(),
                            contenido.getFechaCreacion(), contenido.getStock(), true,
                            ((Musica) contenido).getPkMusica(), ((Musica) contenido).getDiscografica(),
                            ((Musica) contenido).getCanciones());
                    MainController.setView(new VistaMusica(mus));
                }
            }
        } catch (SQLException | ClassNotFoundException e){
            Logger.log(e);
            e.printStackTrace();
            MainController.printToView("Se ha producido un error.");
        }
    }

    public void devolverPrestamo(Contenido contenido){
        try {
            PrestamosModel prestamosModel = new PrestamosModel();
            prestamosModel.devolverPrestamo(contenido);
            if(contenido instanceof Pelicula){
                Pelicula pel = new Pelicula(contenido.getPk(), contenido.getTitulo(), contenido.getCodigo(),
                        contenido.getImagen(), contenido.getFechaCreacion(), contenido.getStock(), false,
                        ((Pelicula) contenido).getPkPelicula(), ((Pelicula) contenido).getProductora(),
                        ((Pelicula) contenido).getDirector(), ((Pelicula) contenido).getActores());
                MainController.setView(new VistaPelicula(pel));
            } else if(contenido instanceof Libro){
                Libro lib = new Libro(contenido.getPk(), contenido.getTitulo(), contenido.getCodigo(), contenido.getImagen(),
                        contenido.getFechaCreacion(), contenido.getStock(), false, ((Libro) contenido).getPkLibro(),
                        ((Libro) contenido).getNumPag(), ((Libro) contenido).getCapituloMuestra());
                MainController.setView(new VistaLibro(lib));
            } else {
                Musica mus = new Musica(contenido.getPk(), contenido.getTitulo(), contenido.getCodigo(), contenido.getImagen(),
                        contenido.getFechaCreacion(), contenido.getStock(), false,
                        ((Musica) contenido).getPkMusica(), ((Musica) contenido).getDiscografica(),
                        ((Musica) contenido).getCanciones());
                MainController.setView(new VistaMusica(mus));
            }
        } catch (SQLException | ClassNotFoundException e){
            Logger.log(e);
            e.printStackTrace();
            MainController.printToView("Se ha producido un error.");
        }
    }
}
