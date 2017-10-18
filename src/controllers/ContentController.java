package controllers;

import dataStructures.Contenido;
import dataStructures.TipoContenido;
import helpers.Logger;
import models.*;
import views.VistaInicio;
import views.VistaPrincipal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentController {

    /**
     * Inicia la home con los contenidos por defecto
     */
    public void initHome(){
        //Instanciar vista
        VistaPrincipal view = new VistaInicio();
        MainController.setView(view);

        Map<TipoContenido, List<Contenido>> respuesta = new HashMap<>();
        respuesta.put(TipoContenido.PRESTAMO, new ArrayList<>());
        respuesta.put(TipoContenido.NOVEDADES, new ArrayList<>());

        try {
            //Coger prestamos
            PrestamosModel prestamosModel = new PrestamosModel();
            List<Contenido> prestamos = prestamosModel.getPrestamos();

            //Coger novedades
            ContenidoModel contentModel = new ContenidoModel();
            List<Contenido> novedades = contentModel.getNovedades();

            //Meterlas en el mapa
            respuesta.put(TipoContenido.PRESTAMO, prestamos);
            respuesta.put(TipoContenido.NOVEDADES, novedades);
        } catch (SQLException | ClassNotFoundException e) {
            //Logear la excepcion
            Logger.log(e);
        }
        //Pintar el resultado a la vista
        MainController.printToView(respuesta);
    }

    /**
     * Inicia la home con los contenidos que se piden
     * @param tipoContenido
     */
    public void initHome(TipoContenido tipoContenido){
        //Inicia la vista
        VistaPrincipal view = new VistaInicio();
        MainController.setView(view);

        Map<TipoContenido, List<Contenido>> respuesta = new HashMap<>();

        try {
            //Se obtiene el contenido
            respuesta = getContenidoByTipo(tipoContenido);
        } catch (SQLException | ClassNotFoundException e) {
            //Se escribe en el log la excepcion
            Logger.log(e);
        }

        //Si el tipo es prestamo o novedades se pintan por filas, si no se pintan todos los contenidos
        if(tipoContenido == TipoContenido.PRESTAMO || tipoContenido == TipoContenido.NOVEDADES) {
            MainController.printToView(respuesta);
        }
        else {
            //Método no hecho aun
            //view.pintarTodo(respuesta);
        }
    }

    /**
     * Coge el contenido correspondiente al tipo que se pide
     * @param tipoContenido
     * @return Mapa con tipo de contenido y lista de contenidos
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private Map<TipoContenido, List<Contenido>> getContenidoByTipo(TipoContenido tipoContenido) throws SQLException, ClassNotFoundException {
        Map<TipoContenido, List<Contenido>> respuesta = new HashMap<>();

        if (tipoContenido == TipoContenido.PRESTAMO){

            //Prestamos
            PrestamosModel prestamosModel = new PrestamosModel();

            respuesta.put(TipoContenido.PRESTAMO_PELICULA, prestamosModel.getPeliculas());
            respuesta.put(TipoContenido.PRESTAMO_LIBRO, prestamosModel.getLibros());
            respuesta.put(TipoContenido.PRESTAMO_MUSICA, prestamosModel.getMusica());
        } else if (tipoContenido == TipoContenido.NOVEDADES){

            //Novedades
            LibroModel libroModel = new LibroModel();
            respuesta.put(TipoContenido.LIBRO, libroModel.getLibros());

            PeliculaModel peliculaModel = new PeliculaModel();
            respuesta.put(TipoContenido.PELICULA, peliculaModel.getPeliculas());

            MusicaModel musicaModel = new MusicaModel();
            respuesta.put(TipoContenido.MUSICA, musicaModel.getMusicas());
        } else if (tipoContenido == TipoContenido.LIBRO){
            //Libros general
            LibroModel libroModel = new LibroModel();
            respuesta.put(TipoContenido.LIBRO, libroModel.getLibros());
        } else if (tipoContenido == TipoContenido.MUSICA){
            //Musica general
            MusicaModel musicaModel = new MusicaModel();
            respuesta.put(TipoContenido.MUSICA, musicaModel.getMusicas());
        } else if (tipoContenido == TipoContenido.PELICULA){
            //Peliculas general
            PeliculaModel peliculaModel = new PeliculaModel();
            respuesta.put(TipoContenido.PELICULA, peliculaModel.getPeliculas());
        } else if (tipoContenido == TipoContenido.PRESTAMO_LIBRO){
            //Libros prestados
            PrestamosModel prestamosModel = new PrestamosModel();
            respuesta.put(TipoContenido.PRESTAMO_LIBRO, prestamosModel.getLibros());
        } else if (tipoContenido == TipoContenido.PRESTAMO_MUSICA){
            //Musica prestada
            PrestamosModel prestamosModel = new PrestamosModel();
            respuesta.put(TipoContenido.PRESTAMO_MUSICA, prestamosModel.getMusica());
        } else if (tipoContenido == TipoContenido.PRESTAMO_PELICULA){
            //Peliculas prestadas
            PrestamosModel prestamosModel = new PrestamosModel();
            respuesta.put(TipoContenido.PRESTAMO_PELICULA, prestamosModel.getPeliculas());
        }

        return respuesta;
    }
}
