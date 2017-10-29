import java.util.ArrayList;
import java.util.List;

import controllers.MainController;
import dataStructures.Actor;
import dataStructures.Director;
import dataStructures.Pelicula;
import dataStructures.Productora;
import views.VistaElemento;
import views.VistaLogin;
import views.VistaPelicula;
import views.VistaPrincipal;

public class Main {
    public static void main(String[] args){
        VistaPrincipal view = new VistaLogin();
    	MainController controller = new MainController(view);
    }
}