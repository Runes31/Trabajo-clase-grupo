package controllers;

import dataStructures.Contenido;
import dataStructures.TipoContenido;
import helpers.Logger;
import models.ContenidoModel;
import models.PrestamosModel;
import views.VistaPrincipal;
import views.VistaRegistro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentController {

    public void initHome(){
        VistaPrincipal view = new VistaRegistro();
        MainController.setView(view);

        Map<TipoContenido, List<Contenido>> respuesta = new HashMap<>();
        respuesta.put(TipoContenido.PRESTAMO, new ArrayList<>());
        respuesta.put(TipoContenido.NOVEDADES, new ArrayList<>());

        try {
            PrestamosModel prestamosModel = new PrestamosModel();
            List<Contenido> prestamos= prestamosModel.getPrestamos();


            ContenidoModel contentModel = null;
                contentModel = new ContenidoModel();
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
        respuesta.put(tipoContenido, new ArrayList<>());

        if (tipoContenido == TipoContenido.PRESTAMO){
            PrestamosModel prestamosModel = new PrestamosModel();

            respuesta.put(TipoContenido.PRESTAMO_PELICULA, prestamosModel.getPeliculas());
            respuesta.put(TipoContenido.PRESTAMO_LIBRO, prestamosModel.getLibros());
            respuesta.put(TipoContenido.PRESTAMO_MUSICA, prestamosModel.getMusica());

        } else if (tipoContenido == TipoContenido.NOVEDADES){

        }

        MainController.printToView(respuesta);
    }
}
