package model;

import java.util.ArrayList;
import java.util.List;

public class Locatario extends Cliente{
    public Locatario(String nome, String cpf, String email, String dataNascimento) {
        super(nome, cpf, email, dataNascimento);
    }

    List<Contrato> listaContratos = new ArrayList<>();

}
