package models;

import dataStructures.TipoUsuario;
import dataStructures.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

public class LoginModel {
    private ConnectDB con;

    public LoginModel() throws SQLException, ClassNotFoundException {
        con = new ConnectDB();
    }

    public User getUser(String userName, String password){
        String dbPassword = DigestUtils.sha256Hex(password);

        String sql = "SELECT usu_pk, usu_nombre, usu_username, usu_email, tipousu.tipousu_nombre" +
                     "FROM usu_usuarios usu" +
                     "LEFT JOIN tipousu_tipo_usuario tipousu ON tipousu.tipousu_pk = usu.tipousu_tipo_usuario_tipousu_pk" +
                     "WHERE usu_username = ? AND usu_password = ?";
        TipoUsuario tipo = TipoUsuario.translateTipo("Usuario");
        return new User(1, "tal", "tal", "tal", tipo);
    }
}
