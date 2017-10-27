package controllers;

import dataStructures.Libro;
import helpers.Logger;
import models.ModelException;
import models.LibroModel;
import views.VistaInicio;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

class LibroController {
    void crearLibro(Libro libro){
        List<String> errores = checkDatos(libro);
        if(errores.isEmpty()) {
            try {
                LibroModel libroModel = new LibroModel();
                libroModel.createLibro(libro);
                MainController.setView(new VistaInicio());
            } catch (SQLException | ClassNotFoundException | ModelException e) {
                Logger.log(e);
                MainController.printToView("Se ha producido un error.");
            }
        } else {
            MainController.printToView(errores);
        }
    }

    private List<String> checkDatos(Libro libro) {
        ContentController contentController = new ContentController();
        List<String> errores = contentController.checkDatos(libro);

        try{
            Integer.parseInt(String.valueOf(libro.getNumPag()));
        } catch (NumberFormatException e){
            errores.add("Debe introducir un número válido de páginas.");
        }

        File f = new File(libro.getCapituloMuestra());
        if(f.isDirectory() || !f.exists()){
            errores.add("Debe introducir un archivo válido.");
        }

        return errores;
    }

    void borrarLibro(Libro libro){
        try{
            LibroModel libroModel =  new LibroModel();
            libroModel.deleteLibro(libro);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }

    void actualizarLibro(Libro libro){
        List<String> errores = checkDatos(libro);
        if(errores.isEmpty()) {
            try {
                LibroModel libroModel = new LibroModel();
                libroModel.updateLibro(libro);
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
