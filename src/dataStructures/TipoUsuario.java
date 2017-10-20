package dataStructures;

public enum TipoUsuario {
    ADMIN, USER;

    public static TipoUsuario stringToTipo(String tipo){
        switch (tipo){
            case "Administrador": return ADMIN;
            case "Usuario": return USER;
            default: return USER;
        }
    }

    public static String TipoToDBString(TipoUsuario tipoUsuario){
        switch (tipoUsuario){
            case ADMIN: return "Administrador";
            default: return "Usuario";
        }
    }
}
