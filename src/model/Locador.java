package model;

import java.util.ArrayList;
import java.util.List;

public class Locador extends Cliente{
    public Locador(String nome, String cpf, String email, String dataNascimento) {
        super(nome, cpf, email, dataNascimento);
    }

    List<Contrato> contratos = new ArrayList<>();
    List<Imoveis> imoveisParaAlugar = new ArrayList<>();



}
