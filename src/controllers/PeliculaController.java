package controllers;

import dataStructures.Actor;
import dataStructures.Pelicula;
import helpers.Logger;
import models.ModelException;
import models.PeliculaModel;
import views.VistaInicio;

import java.sql.SQLException;
import java.util.List;

public class PeliculaController {
    public void crearPelicula(Pelicula pelicula){
        List<String> errores = checkDatos(pelicula);
        if(errores.isEmpty()) {
            try {
                PeliculaModel peliculaModel = new PeliculaModel();
                peliculaModel.createPelicula(pelicula);
                MainController.setView(new VistaInicio());
            } catch (SQLException | ClassNotFoundException | ModelException e) {
                Logger.log(e);
                MainController.printToView("Se ha producido un error.");
            }
        } else {
            MainController.printToView(errores);
        }
    }

    private List<String> checkDatos(Pelicula pelicula) {
        ContentController contentController = new ContentController();
        List<String> errores = contentController.checkDatos(pelicula);

        if(pelicula.getDirector().getNombre().trim().isEmpty()){
            errores.add("Debe introducir el nombre del director.");
        }

        if(pelicula.getProductora().getNombre().trim().isEmpty()){
            errores.add("Debe introducir el nombre de la productora");
        }

        if (pelicula.getActores().isEmpty()){
            errores.add("Debe introducir algún actor");
        } else {
            for (Actor a : pelicula.getActores()){
                if (a.getNombre().trim().isEmpty()){
                    errores.add("Los nombres de los actores no pueden estar vacíos.");
                }
            }
        }

        return errores;
    }

    public void borrarPelicula(Pelicula pelicula){
        try{
            PeliculaModel peliculaModel =  new PeliculaModel();
            peliculaModel.deletePelicula(pelicula);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }

    public void actualizarPelicula(Pelicula pelicula){
        List<String> errores = checkDatos(pelicula);
        if(errores.isEmpty()) {
            try{
                PeliculaModel peliculaModel =  new PeliculaModel();
                peliculaModel.updatePelicula(pelicula);
                MainController.setView(new VistaInicio());
            } catch (SQLException | ClassNotFoundException e) {
                Logger.log(e);
                MainController.printToView("Se ha producido un error.");
            }
        } else {
            MainController.printToView(errores);
        }
    }
}
