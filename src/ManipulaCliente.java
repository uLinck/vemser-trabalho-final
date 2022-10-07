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
            System.out.println("id= "+i + clientes);
        }
    }

    @Override
    public boolean atualizar(int idx, Cliente clienteNovo) {
        Cliente editaCliente = clientes.get(idx);
        editaCliente.setNome(clienteNovo.getNome());
        editaCliente.setCpf(clienteNovo.getCpf());
        editaCliente.setTelefone(clienteNovo.getTelefone());
        editaCliente.setEmail(clienteNovo.getEmail());
        editaCliente.setTipoCliente(clienteNovo.getTipoCliente());
        return true;
    }

    @Override
    public boolean deletar(int idx) {
        clientes.remove(idx);
        return true;
    }

    public void buscarCliente(String cpf){
        Optional<Cliente> retornoCliente = clientes.stream()
                .filter(cliente -> cliente.getCpf().contains(cpf))
                .findFirst();

        if(retornoCliente.isPresent()){
            System.out.println(retornoCliente);
        } else {
            System.err.println("Cliente não encontrado.");
        }
    }
}
