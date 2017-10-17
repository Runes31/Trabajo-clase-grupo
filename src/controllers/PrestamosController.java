package controllers;

import dataStructures.Contenido;
import models.PrestamosModel;

import java.sql.SQLException;

public class PrestamosController {
    public void hacerPrestamo(Contenido contenido) throws SQLException {
        PrestamosModel prestamosModel = new PrestamosModel();
        if(prestamosModel.hayStock(contenido)){
           prestamosModel.hacerPrestamo(contenido);
           MainController.printToView("Prestamo realizado correctamente.");
        } else {
            MainController.printToView("No hay stock.");
        }
    }
}
