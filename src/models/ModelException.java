package models;

class ModelException extends Exception {
    public ModelException(Exception e){
        super(e.getMessage());
    }
}
