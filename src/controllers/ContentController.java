package controllers;

import dataStructures.*;
import helpers.Logger;
import models.*;
import views.VistaInicio;
import views.VistaPrincipal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class ContentController {

    /**
     * Inicia la home con los contenidos por defecto
     */
    public void initHome(){
        //Instanciar vista
        VistaPrincipal view = new VistaInicio();
        MainController.setView(view);

        Map<TipoContenido, List<Contenido>> respuesta = new LinkedHashMap<>();
        respuesta.put(TipoContenido.PRESTAMO, new ArrayList<>());
        respuesta.put(TipoContenido.NOVEDADES, new ArrayList<>());

        try {
            //Coger prestamos
            PrestamosModel prestamosModel = new PrestamosModel();
            List<Contenido> prestamos = prestamosModel.getLibros();
            prestamos.addAll(prestamosModel.getMusica());
            prestamos.addAll(prestamosModel.getPeliculas());

            //Coger novedades
            List<Contenido> novedades = new ArrayList<>();
            LibroModel libroModel = new LibroModel();
            novedades.addAll(libroModel.getLibros());
            PeliculaModel peliculaModel = new PeliculaModel();
            novedades.addAll(peliculaModel.getPeliculas());
            MusicaModel musicaModel = new MusicaModel();
            novedades.addAll(musicaModel.getMusica());

            //Ordenarlas de más nueva a más antigua
            Collections.sort(novedades);


            //Meterlas en el mapa
            respuesta.put(TipoContenido.PRESTAMO, prestamos);
            respuesta.put(TipoContenido.NOVEDADES, novedades);
        } catch (SQLException | ClassNotFoundException e) {
            //Logear la excepcion
            Logger.log(e);
            e.printStackTrace();
        }
        //Pintar el resultado a la vista
        VistaInicio vista = (VistaInicio) MainController.getView();
        vista.pintarContenido(respuesta);
    }

    /**
     * Inicia la home con los contenidos que se piden
     * @param tipoContenido
     */
    public void initHome(TipoContenido tipoContenido){
        //Inicia la vista
        VistaPrincipal view = new VistaInicio();
        MainController.setView(view);

        Map<TipoContenido, List<Contenido>> respuesta = new LinkedHashMap<>();

        try {
            //Se obtiene el contenido
            respuesta = getContenidoByTipo(tipoContenido);
        } catch (SQLException | ClassNotFoundException e) {
            //Se escribe en el log la excepcion
            Logger.log(e);
            e.printStackTrace();
        }

        VistaInicio vista = (VistaInicio) MainController.getView();
        //Si el tipo es prestamo o novedades se pintan por filas, si no se pintan todos los contenidos
        if(tipoContenido == TipoContenido.PRESTAMO || tipoContenido == TipoContenido.NOVEDADES) {
            vista.pintarContenido(respuesta);
        }
        else {
            Map.Entry<TipoContenido, List<Contenido>> entry = respuesta.entrySet().iterator().next();
            List<Contenido> lista = entry.getValue();
            vista.pintarContenido(lista);
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
        Map<TipoContenido, List<Contenido>> respuesta = new LinkedHashMap<>();

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
            respuesta.put(TipoContenido.MUSICA, musicaModel.getMusica());
        } else if (tipoContenido == TipoContenido.LIBRO){
            //Libros general
            LibroModel libroModel = new LibroModel();
            respuesta.put(TipoContenido.LIBRO, libroModel.getLibros());
        } else if (tipoContenido == TipoContenido.MUSICA){
            //Musica general
            MusicaModel musicaModel = new MusicaModel();
            respuesta.put(TipoContenido.MUSICA, musicaModel.getMusica());
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

    List<String> checkDatos(Contenido contenido) {
        List<String> errores = new ArrayList<>();

        if (!contenido.getImagen().isEmpty()){
            try {
                Image image = ImageIO.read(new File(contenido.getImagen()));
                if (image == null) {
                    errores.add("Debe introducir una imagen válida.");
                }
                contenido.copyImageToLocal();
            } catch (IOException e) {
                errores.add("Se ha producido un error.");
                Logger.log(e);
                e.printStackTrace();
                return errores;
            }
        }

        if (contenido.getCodigo().trim().isEmpty()) {
            errores.add("Debe introducir un código.");
        }

        if (contenido.getTitulo().trim().isEmpty()) {
            errores.add("Debe introducir un título.");
        }

        try{
            Integer.parseInt(String.valueOf(contenido.getStock()));
        } catch (NumberFormatException e){
            errores.add("Debe introducir un número de stock válido.");
        }

        return errores;
    }

    public void buscarContenido(String titulo){

        try {
            List<Contenido> result = getContenidoBusqueda(titulo);

            MainController.setView(new VistaInicio());
            VistaInicio vistaInicio = (VistaInicio) MainController.getView();

            vistaInicio.pintarContenido(result);
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            e.printStackTrace();
            MainController.printToView("Se ha producido un error");
        }

    }

    private List<Contenido> getContenidoBusqueda(String titulo) throws SQLException, ClassNotFoundException {
        List<Contenido> resultado = new ArrayList<>();
        LibroModel libroModel = new LibroModel();
        resultado.addAll(libroModel.getLibros(titulo));

        PeliculaModel peliculaModel = new PeliculaModel();
        resultado.addAll(peliculaModel.getPeliculas(titulo));

        MusicaModel musicaModel = new MusicaModel();
        resultado.addAll(musicaModel.getMusica(titulo));

        return resultado;
    }

    public void actualizarContenido(Contenido contenido){
        if(contenido instanceof Libro){
            LibroController libroController = new LibroController();
            libroController.actualizarLibro((Libro) contenido);
        } else if(contenido instanceof Pelicula){
            PeliculaController peliculaController = new PeliculaController();
            peliculaController.actualizarPelicula((Pelicula) contenido);
        } else if(contenido instanceof Musica){
            MusicaController musicaController = new MusicaController();
            musicaController.actualizarMusica((Musica) contenido);
        }
    }

    public void borrarContenido(Contenido contenido){
        if(contenido instanceof Libro){
            LibroController libroController = new LibroController();
            libroController.borrarLibro((Libro) contenido);
        } else if(contenido instanceof Pelicula){
            PeliculaController peliculaController = new PeliculaController();
            peliculaController.borrarPelicula((Pelicula) contenido);
        } else if(contenido instanceof Musica){
            MusicaController musicaController = new MusicaController();
            musicaController.borrarMusica((Musica) contenido);
        }
    }

    public void crearContenido(Contenido contenido){
        if(contenido instanceof Libro){
            LibroController libroController = new LibroController();
            libroController.crearLibro((Libro) contenido);
        } else if(contenido instanceof Pelicula){
            PeliculaController peliculaController = new PeliculaController();
            peliculaController.crearPelicula((Pelicula) contenido);
        } else if(contenido instanceof Musica){
            MusicaController musicaController = new MusicaController();
            musicaController.crearMusica((Musica) contenido);
        }
    }
}