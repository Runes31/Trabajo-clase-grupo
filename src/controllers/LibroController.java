package controllers;

import dataStructures.Libro;
import helpers.Logger;
import models.ModelException;
import models.LibroModel;
import views.VistaInicio;

import java.sql.SQLException;

public class LibroController {
    public void crearLibro(Libro libro){
        try{
            LibroModel libroModel =  new LibroModel();
            libroModel.createLibro(libro);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException | ModelException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }

    public void borrarLibro(Libro libro){
        try{
            LibroModel libroModel =  new LibroModel();
            libroModel.deleteLibro(libro);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }

    public void actualizarLibro(Libro libro){
        try{
            LibroModel libroModel =  new LibroModel();
            libroModel.updateLibro(libro);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }
}
