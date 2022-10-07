package model;

import java.util.ArrayList;
import java.util.List;

public class ManipulaContrato implements Crud<Contrato>{
    List<Contrato> contratos = new ArrayList<>();

    @Override
    public boolean criar(Contrato obj) {
        return false;
    }

    @Override
    public void listar() {

    }

    @Override
    public boolean atualizar(int idx, Contrato obj) {
        return false;
    }

    @Override
    public boolean deletar(int idx) {
        return false;
    }
}
