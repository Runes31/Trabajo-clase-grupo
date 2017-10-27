package controllers;

import dataStructures.TipoUsuario;
import dataStructures.User;
import helpers.Logger;
import helpers.TipoLog;
import models.UserModel;
import org.apache.commons.validator.routines.EmailValidator;
import views.VistaLogin;

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

    /**
     * Check if the user exists in the database and change the view to the home if it does
     * @param username
     * @param password
     */
    public void login(String username, String password){
        //Log the try
        Logger.log("User login("+username+")");

        try {
            //Check if the user exists
            UserModel userModel = new UserModel();
            User user = userModel.getUser(username, password);

            //If it doesnt print it to view
            if(user == null){
                Logger.log("Login failed("+username+")");
                MainController.printToView("Error en el usuario o contraseña");
            } else {
                Logger.log("Login correct("+username+")");

                //Log in the user
                setCurrentUser(user);

                //Change the view to the home
                ContentController contentController = new ContentController();
                contentController.initHome();
            }
        } catch (SQLException | ClassNotFoundException e) {
            //Print the error to the view and log it
            MainController.printToView("Se ha producido un error.");

            Logger.log("Exception in login", TipoLog.ERROR);
            Logger.log(e);
        }
    }

    /**
     * Deletes the session and changes view to login
     */
    public static void logout(){
        currentUser = null;
        MainController.setView(new VistaLogin());
    }

    /**
     * Checks if the data is valid and the user doesn't exist and saves it to the database
     * @param usuario
     * @param password
     * @param passwordConfirmation
     */
    public void registrarUsuario(User usuario, String password, String passwordConfirmation){
        //Check if data is valid
        List<String> messages = new ArrayList<>();
        try {
             messages = userDataIsValid(usuario, password, passwordConfirmation);
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            messages.add("Se ha producido un error.");
        }

        //If it is save it to the database
        if(messages.isEmpty()) {
            try {
                //Save to database and get the insert id
                UserModel userModel = new UserModel();
                int pk = userModel.registrarUsuario(usuario, password);

                //Log in the user
                setCurrentUser(new User(pk, usuario.getUserName(), usuario.getNombre(), usuario.getEmail(), TipoUsuario.USER));

                //Save to log
                Logger.log("New user("+usuario.getUserName()+")");

                //Change the view to the home
                ContentController contentController = new ContentController();
                contentController.initHome();
            } catch (SQLException | ClassNotFoundException e) {
                //Print the error to the view
                MainController.printToView("Se ha producido un error.");

                //Log it
                Logger.log("Exception in registration", TipoLog.ERROR);
                Logger.log(e);
            }
        } else {
            //Print errors to the view
            MainController.printToView(messages);
        }
    }

    /**
     * Checks if the user data is valid
     * @param usuario
     * @param pass
     * @param passConfirm
     * @return
     */
    private List<String> userDataIsValid(User usuario, String pass, String passConfirm) throws SQLException, ClassNotFoundException {
        List<String> errores = new ArrayList<>();

        UserModel userModel = new UserModel();
        if(usuario.getUserName().trim().isEmpty()) {
            errores.add("El alias es obligatorio.");
        } else {
                if(userModel.userNameExists(usuario) != null){
                    errores.add("Ya existe un usuario con ese alias.");
                }
        }

        EmailValidator emailValidator = EmailValidator.getInstance();

        if(usuario.getEmail().trim().isEmpty()){
            errores.add("Debe introducir un email válido.");
        } else if(!emailValidator.isValid(usuario.getEmail())){
            errores.add("Debe introducir un email válido.");
        } else {
            if(userModel.emailExists(usuario) != null){
                errores.add("Ya existe un usuario con ese email");
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

        return errores;
    }


    //TODO: Create a method for updating without modifying the pass
    /**
     * Updates the user in the database and in current memory if the data is valid
     * @param usuario
     * @param password
     * @param passwordConfirmation
     */
    public void actualizarUsuario(User usuario, String password, String passwordConfirmation){
        //Check if data is valid
        List<String> messages = new ArrayList<>();
        try {
            messages = userDataIsValid(usuario, password, passwordConfirmation);
        } catch (SQLException | ClassNotFoundException e) {
            Logger.log(e);
            messages.add("Se ha producido un error.");
        }

        //If it is update the user
        if(messages.isEmpty()) {
            try {
                //Update the user
                UserModel userModel = new UserModel();
                userModel.actualizarUsuario(usuario, password);

                //Change data in memory
                String previousUsername = currentUser.getUserName();
                setCurrentUser(usuario);

                //Log result
                Logger.log("Updated user ("+previousUsername+") -> " + usuario.getUserName());

                //Print result to view

            } catch (SQLException | ClassNotFoundException e) {
                //Print error to view
                MainController.printToView("Se ha producido un error.");

                //Log the error
                Logger.log("Exception in registration", TipoLog.ERROR);
                Logger.log(e);
            }
        } else {
            //Print errors to view
            MainController.printToView(messages);
        }
    }
}
