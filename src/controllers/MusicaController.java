package controllers;

import dataStructures.Cancion;
import dataStructures.Musica;
import helpers.Logger;
import models.ModelException;
import models.MusicaModel;
import views.VistaInicio;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

class MusicaController {
    void crearMusica(Musica musica){
        List<String> errores = checkDatos(musica);
        if(errores.isEmpty()) {
            try {
                MusicaModel musicaModel = new MusicaModel();
                musicaModel.createMusica(musica);
                MainController.setView(new VistaInicio());
            } catch (SQLException | ClassNotFoundException | ModelException e) {
                Logger.log(e);
                MainController.printToView("Se ha producido un error.");
            }
        } else {
            MainController.printToView(errores);
        }
    }

    private List<String> checkDatos(Musica musica) {
        ContentController contentController = new ContentController();
        List<String> errores = contentController.checkDatos(musica);

        if(musica.getDiscografica().getNombre().trim().isEmpty()){
            errores.add("Debe introducir una discográfica.");
        }

        if(musica.getCanciones().isEmpty()){
            errores.add("Debe introducir alguna canción");
        } else {
            for (Cancion c : musica.getCanciones()){
                if(c.getNombre().trim().isEmpty()){
                    errores.add("Los nombres de las canciones no pueden estar vacíos.");
                }
            }
        }

        return errores;
    }

    void borrarMusica(Musica musica){
        try{
            MusicaModel musicaModel =  new MusicaModel();
            musicaModel.deleteMusica(musica);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }

    void actualizarMusica(Musica musica){
        List<String> errores = checkDatos(musica);
        if(errores.isEmpty()) {
            try {
                MusicaModel musicaModel = new MusicaModel();
                musicaModel.updateMusica(musica);
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
