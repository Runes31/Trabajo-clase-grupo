package controllers;

import views.VistaPrincipal;

public class MainController {
    private static VistaPrincipal currentView;

    public MainController(VistaPrincipal view){
        currentView = view;
    }

    public static void setView(VistaPrincipal view){
        currentView.setVisible(false);
        currentView = view;
    }

    static void printToView(Object data){
        currentView.pintar(data);
    }
}
