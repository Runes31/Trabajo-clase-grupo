package controllers;

import dataStructures.Musica;
import helpers.Logger;
import models.ModelException;
import models.MusicaModel;
import views.VistaInicio;

import java.sql.SQLException;

public class MusicaController {
    public void crearMusica(Musica musica){
        try{
            MusicaModel musicaModel =  new MusicaModel();
            musicaModel.createMusica(musica);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException | ModelException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }

    public void borrarMusica(Musica musica){
        try{
            MusicaModel musicaModel =  new MusicaModel();
            musicaModel.deleteMusica(musica);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }

    public void actualizarMusica(Musica musica){
        try{
            MusicaModel musicaModel =  new MusicaModel();
            musicaModel.updateMusica(musica);
            MainController.setView(new VistaInicio());
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            MainController.printToView("Se ha producido un error.");
        }
    }
}
