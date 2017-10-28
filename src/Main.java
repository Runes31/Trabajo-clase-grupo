import controllers.MainController;
import views.VistaElemento;
import views.VistaLogin;
import views.VistaPrincipal;

public class Main {
    public static void main(String[] args){
    	VistaPrincipal view = new VistaLogin();
    	MainController controller = new MainController(view);

    }
}