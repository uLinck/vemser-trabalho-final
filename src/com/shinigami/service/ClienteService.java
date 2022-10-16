package model.com.shinigami.service;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.Cliente;
import model.com.shinigami.repository.ClienteRepository;

import java.util.List;

public class ClienteService implements Service<Cliente>{
    private ClienteRepository clienteRepository;

    public ClienteService() {
        clienteRepository = new ClienteRepository();
    }

    @Override
    public boolean adicionar(Cliente pessoa)throws DadoInvalidoException {
        try {

            validaCpf(pessoa);
            validaNome(pessoa);
            validaEmail(pessoa);
            Cliente pessoaAdicionada = clienteRepository.adicionar(pessoa);
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return false;
        } catch (DadoInvalidoException e){
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remover(Integer id) throws DadoInvalidoException{
        try {
            boolean conseguiuRemover = clienteRepository.remover(id);
            System.out.println("cliente removido? " + conseguiuRemover + "| com id=" + id);
            return conseguiuRemover;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void editar(Integer id, Cliente pessoa) throws DadoInvalidoException {
        try {
            boolean conseguiuEditar = clienteRepository.editar(id, pessoa);
            System.out.println("cliente editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listar() {
        try {
            List<Cliente> listar = clienteRepository.listar();
            listar.forEach(cliente ->{
                System.out.println("id:"+cliente.getIdCliente()+" | Nome: "+cliente.getNome());
            });
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Cliente buscarCliente(String busca)throws BancoDeDadosException{
        try {
            int id = Integer.parseInt(busca.trim());
            return clienteRepository.buscarCliente(id);
        }catch (BancoDeDadosException e){
            throw new BancoDeDadosException(e.getCause());
        }catch(Exception e){
            return clienteRepository.buscarCliente(busca);
        }
    }

    private boolean validaCpf(Cliente cliente) throws DadoInvalidoException {
        if (cliente.getCpf().length() != 11) {
            throw new DadoInvalidoException("CPF Invalido!");
        }
        return true;
    }
    private boolean validaNome(Cliente cliente) throws DadoInvalidoException{
        if(cliente.getNome().equals(null) || cliente.getNome().isBlank()){
            throw new DadoInvalidoException("Nome Invalido!");
        }
        return true;
    }

    private boolean validaEmail(Cliente cliente) throws DadoInvalidoException{
        if(cliente.getEmail().isBlank() || !cliente.getEmail().contains("@")){
            throw new DadoInvalidoException("Email invalido!");
        }
        return true;
    }




}