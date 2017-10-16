package controllers;

import dataStructures.User;
import helpers.Logger;
import helpers.TipoLog;
import models.LoginModel;
import views.VistaLogin;
import views.VistaPrincipal;
import views.VistaRegistro;

import java.sql.SQLException;

public class LoginController {

    public void login(String username, String password){
        Logger.log("User login("+username+")");

        try {
            LoginModel loginModel = new LoginModel();
            User user = loginModel.getUser(username, password);

            if(user == null){
                Logger.log("Login failed("+username+")");
                //TODO: Print failed login to view
            } else {
                Logger.log("Login correct("+username+")");
                UserController.pk = user.getPk();
                UserController.userName = user.getUserName();

                //TODO: swap to correct view
                VistaPrincipal view = new VistaRegistro();
                MainController.setView(view);
            }
        } catch (SQLException | ClassNotFoundException e) {
            //TODO: Handle exception (Print error to view)
            Logger.log("Exception in login", TipoLog.ERROR);
            Logger.log(e);
        }
    }
}
