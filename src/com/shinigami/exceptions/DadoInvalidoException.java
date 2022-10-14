package model.com.shinigami.exceptions;

public class DadoInvalidoException extends Exception{
    public DadoInvalidoException() {
        super("DADO INVALIDO!\n");
    }

    public DadoInvalidoException(String message) {
        super(message);
    }
}
