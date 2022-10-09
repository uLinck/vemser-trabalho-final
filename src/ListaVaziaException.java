package model;

public class ListaVaziaException extends Exception{
    public ListaVaziaException() {
        super("LISTA VAZIA\n");
    }

    public ListaVaziaException(String message) {
        super(message);
    }
}
