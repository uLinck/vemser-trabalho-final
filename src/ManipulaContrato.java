package model;

import java.util.ArrayList;
import java.util.List;

public class ManipulaContrato implements Crud<Contrato>{
    List<Contrato> contratos = new ArrayList<>();

    @Override
    public boolean criar(Contrato obj) {
        contratos.add(obj);
        return false;
    }

    @Override
    public void listar() {
        contratos.stream()
                .forEach(contrato -> {
                    System.out.println(contrato);
                });
    }

    @Override
    public boolean atualizar(int idx, Contrato obj) {
        contratos.set(idx,obj);
        return false;
    }

    @Override
    public boolean deletar(int idx) {
        contratos.remove(contratos.get(idx));
        return false;
    }
}
