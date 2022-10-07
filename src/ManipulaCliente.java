package model;

import java.util.ArrayList;
import java.util.List;

public class ManipulaCliente implements Crud{
    List<Cliente> clientes = new ArrayList<>();

    @Override
    public boolean criar() {
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
