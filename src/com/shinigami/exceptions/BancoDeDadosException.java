package model.com.shinigami.exceptions;

import java.sql.SQLException;

public class BancoDeDadosException extends SQLException {
    public BancoDeDadosException(Throwable cause) {
        super(cause);
    }
    public BancoDeDadosException(String erro){
        super(erro);
    }
}
