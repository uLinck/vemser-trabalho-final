package model.com.shinigami.service;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.Endereco;
import model.com.shinigami.repository.EnderecoRepository;

import java.util.List;

public class EnderecoService {
    private EnderecoRepository enderecoRepository;

    public EnderecoService(){
        enderecoRepository = new EnderecoRepository();
    }

    public boolean adicionarEndereco(Endereco endereco) throws DadoInvalidoException{
        try {
            validaEndereco(endereco);
            Endereco enderecoAdicionado = enderecoRepository.adicionar(formataEndereco(endereco));
            System.out.println("Endereco adicinado com sucesso! " + enderecoAdicionado);
            return true;

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return false;
        } catch (DadoInvalidoException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removerEndereco(Integer id){
        try{
            boolean conseguiuRemover = enderecoRepository.remover(id);
            System.out.println("endereco removido? " + conseguiuRemover + "| com id=" + id);
            return conseguiuRemover;
        } catch (BancoDeDadosException e){
            e.printStackTrace();
            return false;
        }
    }

    public void editarEndereco(Integer id, Endereco endereco){
        try {
            boolean conseguiuEditar = enderecoRepository.editar(id, endereco);
            System.out.println("Endereco editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarEndereco() {
        try {
            List<Endereco> listar = enderecoRepository.listar();
            listar.forEach(endereco ->{
                System.out.println("id:"+ endereco.getIdEndereco()+" | Rua: "+endereco.getRua()+" | Estado: "+endereco.getEstado()+" | CEP: "+endereco.getCep());
            });
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Endereco buscarEndereco(Integer id) throws BancoDeDadosException{
        try {
            return enderecoRepository.buscarEndereco(id);
        }catch (BancoDeDadosException e){
            throw new BancoDeDadosException(e.getCause());
        }
    }

    public boolean validaEndereco(Endereco endereco) throws DadoInvalidoException {
        if(endereco.getRua().equals(null)){
            throw new DadoInvalidoException();
        } else if(endereco.getCidade().equals(null)){
            throw new DadoInvalidoException();
        } else if(endereco.getEstado().equals(null)){
            throw new DadoInvalidoException();
        } else if(endereco.getPais().equals(null)){
            throw new DadoInvalidoException();
        } else if((Integer)endereco.getNumero() != null){
            throw new DadoInvalidoException();
        } else if(endereco.getCep().equals(null) || endereco.getCep().length() != 9){
            throw new DadoInvalidoException();
        }
        return true;
    }

    public Endereco formataEndereco(Endereco endereco){
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
