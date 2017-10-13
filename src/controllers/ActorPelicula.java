package controllers;

public class ActorPelicula {
    
    private int pkPelicula;
    private int pkActor;

    public ActorPelicula(int pkPelicula, int pkActor) {
        this.pkPelicula = pkPelicula;
        this.pkActor = pkActor;
    }

    public int getPkPelicula() {
        return pkPelicula;
    }

    public void setPkPelicula(int pkPelicula) {
        this.pkPelicula = pkPelicula;
    }

    public int getPkActor() {
        return pkActor;
    }

    public void setPkActor(int pkActor) {
        this.pkActor = pkActor;
    }
    
    
}
