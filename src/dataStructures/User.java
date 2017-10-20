package dataStructures;

public class User {
    private int pk;
    private String userName;
    private String nombre;
    private String email;
    private TipoUsuario tipoUsuario;

    public User(int pk, String userName, String nombre, String email, TipoUsuario tipoUsuario){
        this(userName, nombre, email);
        this.pk = pk;
        this.tipoUsuario=tipoUsuario;
    }

    public User(String userName, String nombre, String email){
        this.userName = userName;
        this.nombre = nombre;
        this.email = email;
        this.tipoUsuario = TipoUsuario.USER;
        this.pk=0;
    }

    public int getPk() {
        return pk;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
