package dataStructures;

public enum TipoContenido {
    PRESTAMO("Mis préstamos"), NOVEDADES("Novedades"), MUSICA("Música"), PELICULA("Películas"), LIBRO("Libros"),
    PRESTAMO_PELICULA("Mis películas"), PRESTAMO_LIBRO("Mis libros"), PRESTAMO_MUSICA("Mi música");

    private final String name;

    TipoContenido(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
