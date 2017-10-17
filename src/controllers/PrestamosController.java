package controllers;

import dataStructures.Contenido;
import models.PrestamosModel;

public class PrestamosController {
    public void hacerPrestamo(Contenido contenido){
        PrestamosModel prestamosModel = new PrestamosModel();
        if(prestamosModel.hayStock(contenido)){
           prestamosModel.hacerPrestamo(contenido);
           MainController.printToView("Prestamo realizado correctamente.");
        } else {
            MainController.printToView("No hay stock.");
        }
    }
}
