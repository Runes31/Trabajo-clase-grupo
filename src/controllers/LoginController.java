package controllers;

import dataStructures.User;
import models.LoginModel;
import views.VistaLogin;

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
                VistaLogin view = new VistaLogin();
                MainController.setView(view);
            }
        } catch (SQLException | ClassNotFoundException e) {
            //TODO: Handle exception (Print error to view)
            e.printStackTrace();
        }
    }
}
