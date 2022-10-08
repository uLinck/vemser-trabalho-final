package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManipulaCliente implements Crud<Cliente>{
    List<Cliente> clientes = new ArrayList<>();

    @Override
    public boolean criar(Cliente cliente) {
        clientes.add(cliente);
        return true;
    }

    @Override
    public void listar() {
        for(int i=0; i < clientes.size(); i++){
            System.out.print("id: "+i+" | ");
            clientes.get(i).imprimir();
        }
    }

    @Override
    public boolean atualizar(int idx, Cliente clienteNovo) {
        clientes.set(idx, clienteNovo);
        return true;
    }

    @Override
    public boolean deletar(int idx) {
        clientes.remove(idx);
        return true;
    }

    public Cliente buscarCliente(String cpf){
        return clientes.stream()
                .filter(cliente -> cliente.getCpf().contains(cpf))
                .findFirst()
                .get();

    }

    public Cliente buscarCliente(int idx){
        return clientes.get(idx);
    }

}
