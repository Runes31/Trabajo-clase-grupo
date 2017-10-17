package controllers;

import dataStructures.TipoUsuario;
import dataStructures.User;
import helpers.Logger;
import helpers.TipoLog;
import models.UserModel;
import views.VistaPrincipal;
import views.VistaRegistro;
import org.apache.commons.validator.routines.EmailValidator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static User currentUser;

    public static void setCurrentUser(User user){
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public void login(String username, String password){
        Logger.log("User login("+username+")");

        try {
            UserModel userModel = new UserModel();
            User user = userModel.getUser(username, password);

            if(user == null){
                Logger.log("Login failed("+username+")");
                //TODO: Print failed login to view
            } else {
                Logger.log("Login correct("+username+")");
                setCurrentUser(user);

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

    public void registrarUsuario(User usuario, String password, String passwordConfirmation){
        if(userDataIsValid(usuario, password, passwordConfirmation).isEmpty()) {
            try {
                UserModel userModel = new UserModel();
                int pk = userModel.registrarUsuario(usuario, password);

                setCurrentUser(new User(pk, usuario.getUserName(), usuario.getNombre(), usuario.getEmail(), TipoUsuario.USER));
                Logger.log("New user("+usuario.getUserName()+")");
                //TODO: Print to view
            } catch (SQLException | ClassNotFoundException e) {
                //TODO: Print to view
                Logger.log("Exception in registration", TipoLog.ERROR);
                Logger.log(e);
            }
        } else {
            //TODO: Print to view it isn't
        }
    }

    private List<String> userDataIsValid(User usuario, String pass, String passConfirm) {
        List<String> errores = new ArrayList<>();
        if(usuario.getUserName().trim().isEmpty()) {
            errores.add("El username es obligatorio.");
        } else {
            try {
                UserModel userModel = new UserModel();
                if(userModel.userExists(usuario) != null){
                    errores.add("Ya existe un usuario con ese username o email.");
                }
            } catch (SQLException | ClassNotFoundException e) {
                Logger.log(e);
                errores.add("Se ha producido un error.");
                return errores;
            }
        }

        if(usuario.getNombre().trim().isEmpty()){
            errores.add("El nombre es obligatorio.");
        }

        if (pass.length() < 8) {
            errores.add("La contraseña debe ser mayor de 8 caracteres.");
        }

        if (!pass.equals(passConfirm)){
            errores.add("Las contraseñas no coinciden.");
        }

        EmailValidator emailValidator = EmailValidator.getInstance();

        if(usuario.getEmail().trim().isEmpty()){
            errores.add("Debe introducir un email válido.");
        } else if(emailValidator.isValid(usuario.getEmail())){
            errores.add("Debe introducir un email válido.");
        }

        return errores;
    }
}
