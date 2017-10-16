package dataStructures;

public enum TipoUsuario {
    ADMIN, USER;

    public static TipoUsuario translateTipo(String tipo){
        switch (tipo){
            case "Administrador": return ADMIN;
            case "Usuario": return USER;
            default: return USER;
        }
    }
}
