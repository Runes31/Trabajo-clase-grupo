package controllers;

import views.VistaPrincipal;

import java.util.List;

public class MainController {
    private static VistaPrincipal currentView;

    public MainController(VistaPrincipal view){
        currentView = view;
    }

    public static void setView(VistaPrincipal view){
        currentView.dispose();
        currentView = view;
    }

    public static VistaPrincipal getView(){
        return currentView;
    }

    static void printToView(String data){
        currentView.pintar(data);
    }

    static void printToView(List<String> data) {currentView.pintar(data);}
}
