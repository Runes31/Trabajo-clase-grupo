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

    /**
     * This is used to create a new user object with other user's pk and type, usually to pass it to a controller to modify the data of the
     * current user
     * @param u Usually it comes from UserController.getCurrentUser()
     * @param userName
     * @param nombre
     * @param email
     */
    public User(User u, String userName, String nombre, String email){
        this.pk = u.pk;
        this.tipoUsuario = u.tipoUsuario;
        this.userName = userName;
        this.nombre = nombre;
        this.email = email;
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
