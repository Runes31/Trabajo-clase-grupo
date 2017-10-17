package models;

import dataStructures.TipoUsuario;
import dataStructures.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserModel {
    private ConnectDB con;

    public UserModel() throws SQLException, ClassNotFoundException {
        con = new ConnectDB();
    }

    public User getUser(String userName, String password) throws SQLException {
        String sql = "SELECT usu_pk, usu_nombre, usu_username, usu_email, tipousu.tipousu_nombre" +
                     "FROM usu_usuarios usu" +
                     "LEFT JOIN tipousu_tipo_usuario tipousu ON tipousu.tipousu_pk = usu.tipousu_tipo_usuario_tipousu_pk" +
                     "WHERE (usu_username = ? || usu_email = ?) AND usu_password = ?";

        String dbPassword = DigestUtils.sha256Hex(password);

        PreparedStatement selectUser = con.getConn().prepareStatement(sql);
        selectUser.setString(1, userName);
        selectUser.setString(2, userName);
        selectUser.setString(3, dbPassword);

        ResultSet rs = selectUser.executeQuery();
        if(rs.next()) {
            int pk = rs.getInt(1);
            String name = rs.getString(2);
            String userNameResult = rs.getString(3);
            String email = rs.getString(4);
            String tipoString = rs.getString(5);
            TipoUsuario tipo = TipoUsuario.stringToTipo(tipoString);

            return new User(pk, userNameResult, name, email, tipo);
        } else {
            return null;
        }
    }

    public User userExists(User usuario) throws SQLException {
        String sql = "SELECT usu_pk, usu_nombre, usu_username, usu_email, tipousu.tipousu_nombre" +
            "FROM usu_usuarios usu" +
            "LEFT JOIN tipousu_tipo_usuario tipousu ON tipousu.tipousu_pk = usu.tipousu_tipo_usuario_tipousu_pk" +
            "WHERE usu_username = ? OR usu_email = ?";

        PreparedStatement selectUser = con.getConn().prepareStatement(sql);
        selectUser.setString(1, usuario.getUserName());
        selectUser.setString(2, usuario.getEmail());

        ResultSet rs = selectUser.executeQuery();
        if(rs.next()) {
            int pk = rs.getInt(1);
            String name = rs.getString(2);
            String userNameResult = rs.getString(3);
            String email = rs.getString(4);
            String tipoString = rs.getString(5);
            TipoUsuario tipo = TipoUsuario.stringToTipo(tipoString);

            return new User(pk, userNameResult, name, email, tipo);
        } else {
            return null;
        }
    }

    public int registrarUsuario(User usuario, String password) throws SQLException {
        String sql = "INSERT INTO usu_usuarios(usu_nombre, usu_username, usu_password, usu_email, tipousu_tipo_usuario_tipousu_pk)" +
                     "SELECT ?, ?, ?, ?, tipousu_pk FROM tipousu_tipo_usuario" +
                     "WHERE tipousu_nombre = " + TipoUsuario.TipoToDBString(TipoUsuario.USER);

        String dbPassword = DigestUtils.sha256Hex(password);

        PreparedStatement insertUser = con.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        insertUser.setString(1, usuario.getNombre());
        insertUser.setString(2, usuario.getUserName());
        insertUser.setString(3, dbPassword);
        insertUser.setString(4, usuario.getEmail());

        insertUser.executeUpdate();

        ResultSet rs = insertUser.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
}
