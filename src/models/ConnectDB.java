package models;

import java.sql.*;

public class ConnectDB {
    protected Connection conn;
    protected Statement st;
    private static final String USER = "";
    private static final String PASS = "";
    private static final String URL = "";
    private static final String DRIVER = "";

    public Connection getConn(){
        return conn;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if(checkDriver() && conn == null) {
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
    }

    private boolean checkDriver() throws ClassNotFoundException {
        Class.forName(DRIVER);
        return true;
    }

    protected ResultSet executeQuery(String sql) throws SQLException {
        st = conn.createStatement();
        return st.executeQuery(sql);
    }
}
