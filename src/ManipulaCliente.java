package model;

import java.util.ArrayList;
import java.util.List;

public class ManipulaCliente implements Crud<Cliente>{
    List<Cliente> clientes = new ArrayList<>();

    public ManipulaCliente(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    public ManipulaCliente() {
        clientes.add(new Cliente("Eusébio Rabelo Leal","54222440019","eusebio@gmail.com","(21) 3534-7699",TipoCliente.LOCADOR));
        clientes.add(new Cliente("Dinarte Ponte Mateus","29854982017","dinarte@gmail.com","(38) 2511-4199",TipoCliente.LOCADOR));
        clientes.add(new Cliente("Pablo Roriz Granja","77339166076","pablo@gmail.com","(69) 2318-9545",TipoCliente.LOCATARIO));
        clientes.add(new Cliente("Veronica Veríssimo Pêcego","08925438097","veronica@gmail.com","(68) 2867-1182",TipoCliente.LOCATARIO));
    }

    @Override
    public boolean criar(Cliente cliente) {
        clientes.add(cliente);
        return true;
    }

    @Override
    public void listar() throws ListaVaziaException{
        if(clientes.isEmpty()){
            throw new ListaVaziaException();
        }
        for(int i=0; i < clientes.size(); i++){
            System.out.print("id: "+i+" | ");
            System.out.println(clientes.get(i).getNome());
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

    public Cliente buscarCliente(String nome){
        return clientes.stream()
                .filter(cliente -> cliente.getNome().toLowerCase().contains(nome.toLowerCase()))
                .findFirst()
                .get();
    }

    public Cliente buscarCliente(int idx){
        return clientes.get(idx);
    }

    public Cliente buscarCliente(int idx, TipoCliente tipoCliente){
        return clientes.stream()
                .filter(cliente -> cliente.getTipoCliente().equals(tipoCliente))
                .toList()
                .get(idx);
    }
    public void listar(TipoCliente tipoCliente){
        int i = 0;
        List<Cliente> listar= clientes.stream()
                .filter(cliente -> cliente.getTipoCliente().toString().equals(tipoCliente.toString()))
                .toList();

        for(Cliente cliente:listar){
            System.out.print("id: "+i+" | ");
            cliente.imprimir();
            i++;
        }
    }


}
