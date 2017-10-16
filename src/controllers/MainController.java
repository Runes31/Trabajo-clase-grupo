package controllers;

import views.VistaPrincipal;

public class MainController {
    private static VistaPrincipal currentView;

    public MainController(VistaPrincipal view){
        currentView = view;
    }

    public void setView(VistaPrincipal view){
        currentView = view;
    }
}
