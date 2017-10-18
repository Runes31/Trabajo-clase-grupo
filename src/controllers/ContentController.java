package controllers;

import dataStructures.Contenido;
import dataStructures.TipoContenido;
import helpers.Logger;
import models.*;
import views.VistaInicio;
import views.VistaPrincipal;
import views.VistaRegistro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentController {

    public void initHome(){
        VistaPrincipal view = new VistaInicio();
        MainController.setView(view);

        Map<TipoContenido, List<Contenido>> respuesta = new HashMap<>();
        respuesta.put(TipoContenido.PRESTAMO, new ArrayList<>());
        respuesta.put(TipoContenido.NOVEDADES, new ArrayList<>());

        try {
            PrestamosModel prestamosModel = new PrestamosModel();
            List<Contenido> prestamos= prestamosModel.getPrestamos();


            ContenidoModel contentModel = new ContenidoModel();
            List<Contenido> novedades = contentModel.getNovedades();


            respuesta.put(TipoContenido.PRESTAMO, prestamos);
            respuesta.put(TipoContenido.NOVEDADES, novedades);
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
        }

        MainController.printToView(respuesta);
    }

    public void initHome(TipoContenido tipoContenido){
        VistaPrincipal view = new VistaRegistro();
        MainController.setView(view);

        Map<TipoContenido, List<Contenido>> respuesta = new HashMap<>();

        try {
            respuesta = getContenidoByTipo(tipoContenido);
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
        }

        MainController.printToView(respuesta);
    }

    private Map<TipoContenido, List<Contenido>> getContenidoByTipo(TipoContenido tipoContenido) throws SQLException, ClassNotFoundException {
        Map<TipoContenido, List<Contenido>> respuesta = new HashMap<>();

        if (tipoContenido == TipoContenido.PRESTAMO){
            PrestamosModel prestamosModel = new PrestamosModel();

            respuesta.put(TipoContenido.PRESTAMO_PELICULA, prestamosModel.getPeliculas());
            respuesta.put(TipoContenido.PRESTAMO_LIBRO, prestamosModel.getLibros());
            respuesta.put(TipoContenido.PRESTAMO_MUSICA, prestamosModel.getMusica());
        } else if (tipoContenido == TipoContenido.NOVEDADES){
            LibroModel libroModel = new LibroModel();
            respuesta.put(TipoContenido.LIBRO, libroModel.getLibros());

            /* TODO: pelicula model
                PeliculaModel peliculaModel = new PeliculaModel();
                respuesta.put(TipoContenido.PELICULA, peliculaModel.getPeliculas());
            */

            MusicaModel musicaModel = new MusicaModel();
            respuesta.put(TipoContenido.MUSICA, musicaModel.getMusicas());
        } else if (tipoContenido == TipoContenido.LIBRO){
            LibroModel libroModel = new LibroModel();
            respuesta.put(TipoContenido.LIBRO, libroModel.getLibros());
        } else if (tipoContenido == TipoContenido.MUSICA){
            MusicaModel musicaModel = new MusicaModel();
            respuesta.put(TipoContenido.MUSICA, musicaModel.getMusicas());
        } else if (tipoContenido == TipoContenido.PELICULA){
            PeliculaModel peliculaModel = new PeliculaModel();
            respuesta.put(TipoContenido.PELICULA, peliculaModel.getPeliculas());
        } else if (tipoContenido == TipoContenido.PRESTAMO_LIBRO){
            PrestamosModel prestamosModel = new PrestamosModel();
            respuesta.put(TipoContenido.PRESTAMO_LIBRO, prestamosModel.getLibros());
        } else if (tipoContenido == TipoContenido.PRESTAMO_MUSICA){
            PrestamosModel prestamosModel = new PrestamosModel();
            respuesta.put(TipoContenido.PRESTAMO_MUSICA, prestamosModel.getMusica());
        } else if (tipoContenido == TipoContenido.PRESTAMO_PELICULA){
            PrestamosModel prestamosModel = new PrestamosModel();
            respuesta.put(TipoContenido.PRESTAMO_PELICULA, prestamosModel.getPeliculas());
        }

        return respuesta;
    }
}
