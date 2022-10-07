package model;

import java.util.ArrayList;
import java.util.List;

public class ManipulaContrato implements Crud{
    List<Contrato> contratos = new ArrayList<>();
    @Override
    public boolean criar() {
        Contrato contrato = new Contrato();
        return false;
    }

    @Override
    public void listar() {

    }

    @Override
    public boolean atualizar() {
        return false;
    }

    @Override
    public boolean deletar() {
        return false;
    }
}
