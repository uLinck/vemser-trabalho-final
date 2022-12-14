package model.com.shinigami.service;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.Endereco;
import model.com.shinigami.repository.EnderecoRepository;

import java.util.List;

public class EnderecoService implements Service<Endereco> {
    private EnderecoRepository enderecoRepository;

    public EnderecoService(){
        enderecoRepository = new EnderecoRepository();
    }

    @Override
    public boolean adicionar(Endereco endereco) throws DadoInvalidoException{
        try {
            validaCidade(endereco.getCidade());
            validaCep(endereco.getCep());
            validaEstado(endereco.getEstado());
            validaPais(endereco.getPais());
            validaNumero(endereco.getNumero());
            validaRua(endereco.getRua());
            enderecoRepository.adicionar(formataEndereco(endereco));
            System.out.println("Endereco adicinado com sucesso!");
            return true;

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return false;
        } catch (DadoInvalidoException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public boolean remover(Integer id) throws DadoInvalidoException{
        try{
            boolean conseguiuRemover = enderecoRepository.remover(id);
            return conseguiuRemover;
        } catch (BancoDeDadosException e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public void editar(Integer id, Endereco endereco) throws DadoInvalidoException{
        try {
            boolean conseguiuEditar = enderecoRepository.editar(id, endereco);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void listar() {
        try {
            List<Endereco> listar = enderecoRepository.listar();
            listar.forEach(endereco ->{
                System.out.println("id:"+ endereco.getIdEndereco()+" | Rua: "+endereco.getRua()+" | Estado: "+endereco.getEstado()+" | CEP: "+endereco.getCep());
            });
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public boolean validaRua(String  rua) throws DadoInvalidoException{
        if(rua.isBlank()) {
            throw new DadoInvalidoException("Rua Invalida");
        }
        return true;
    }

    public boolean validaNumero(int numero) throws DadoInvalidoException{
        if(numero < 0) {
            throw new DadoInvalidoException("Numero Invalido");
        }
        return true;
    }

    public boolean validaCep(String cep) throws DadoInvalidoException{
        if(cep.trim().length()!= 9){
            throw new DadoInvalidoException("Cep Invalido!");
        }
        return true;
    }


    public boolean validaCidade(String cidade) throws DadoInvalidoException{
        if(cidade.isBlank()){
            throw new DadoInvalidoException("Cidade Invalida!");
        }
        return true;
    }
    public boolean validaEstado(String estado) throws DadoInvalidoException{
        if(estado.isBlank()){
            throw new DadoInvalidoException("Estado Invalido!");
        }
        return true;
    }
    public boolean validaPais(String pais) throws DadoInvalidoException{
        if(pais.isBlank()){
            throw new DadoInvalidoException("Pais Invalido!");
        }
        return true;
    }

    private Endereco formataEndereco(Endereco endereco){
        Endereco enderecoFormatado = new Endereco();

        enderecoFormatado.setIdEndereco(endereco.getIdEndereco());
        enderecoFormatado.setRua(endereco.getRua().trim());
        enderecoFormatado.setCidade(endereco.getCidade().trim());
        enderecoFormatado.setEstado(endereco.getEstado().trim());
        enderecoFormatado.setPais(endereco.getPais().trim());
        enderecoFormatado.setComplemento(endereco.getComplemento().trim());
        enderecoFormatado.setNumero(endereco.getNumero());
        enderecoFormatado.setCep(endereco.getCep().trim());

        return enderecoFormatado;
    }
}
