package model;

import java.util.ArrayList;
import java.util.List;

public class ManipulaCliente implements Crud<Cliente>{
    List<Cliente> clientes = new ArrayList<>();

    @Override
    public boolean criar(Cliente obj) {
        return false;
    }

    @Override
    public void listar() {

    }

    @Override
    public boolean atualizar(int idx, Cliente obj) {
        return false;
    }

    @Override
    public boolean deletar(int idx) {
        return false;
    }
}
