package controllers;

import dataStructures.User;
import helpers.Logger;
import models.LoginModel;
import views.VistaLogin;
import views.VistaPrincipal;
import views.VistaRegistro;

import java.sql.SQLException;

public class LoginController {

    public void login(String username, String password){
        try {
            LoginModel loginModel = new LoginModel();
            User user = loginModel.getUser(username, password);

            if(user == null){
                //TODO: Print failed login to view
            } else {
                UserController.pk = user.getPk();
                UserController.userName = user.getUserName();

                //TODO: swap to correct view
                VistaPrincipal view = new VistaRegistro();
                MainController.setView(view);
            }
        } catch (SQLException | ClassNotFoundException e) {
            //TODO: Handle exception (Print error to view)
            Logger.log(e);
        }
    }
}
