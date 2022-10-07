package model;

import java.util.ArrayList;
import java.util.List;

public class Locatario extends Cliente implements Impressao{
    public Locatario(String nome, String cpf, String email, String dataNascimento) {
        super(nome, cpf, email, dataNascimento);
    }

    List<Contrato> contratos = new ArrayList<>();

    @Override
    public void impressao() {

    }
}
