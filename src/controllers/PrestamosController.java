package controllers;

import dataStructures.Contenido;
import helpers.Logger;
import models.PrestamosModel;

import java.sql.SQLException;

public class PrestamosController {
    private static final int MAX_NUM_PRESTAMOS = 10;

    public void hacerPrestamo(Contenido contenido) {
        try {
            PrestamosModel prestamosModel = new PrestamosModel();
            if (!prestamosModel.hayStock(contenido)) {
                MainController.printToView("No hay stock.");
            } else if(prestamosModel.getNumPrestamos() >= MAX_NUM_PRESTAMOS) {
                MainController.printToView("No puedes realizar más de " + MAX_NUM_PRESTAMOS + " a la vez.");
            } else {
                prestamosModel.hacerPrestamo(contenido);
                MainController.printToView("Prestamo realizado correctamente.");
            }
        } catch (SQLException | ClassNotFoundException e){
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }

    public void devolverPrestamo(Contenido contenido){
        try {
            PrestamosModel prestamosModel = new PrestamosModel();
            prestamosModel.devolverPrestamo(contenido);
            MainController.printToView("Devolución realizada con éxito.");
        } catch (SQLException | ClassNotFoundException e){
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }
}
