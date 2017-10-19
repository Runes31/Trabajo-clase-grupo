package controllers;

import dataStructures.Pelicula;
import helpers.Logger;
import models.ModelException;
import models.PeliculaModel;
import views.VistaInicio;

import java.sql.SQLException;

public class PeliculaController {
    public void crearPelicula(Pelicula pelicula){
        try{
            PeliculaModel peliculaModel =  new PeliculaModel();
            peliculaModel.createPelicula(pelicula);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException | ModelException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
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
        try{
            PeliculaModel peliculaModel =  new PeliculaModel();
            peliculaModel.updatePelicula(pelicula);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }
}
