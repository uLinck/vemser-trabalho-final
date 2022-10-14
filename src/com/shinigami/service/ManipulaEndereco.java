package model.com.shinigami.service;

import model.com.shinigami.exceptions.ListaVaziaException;
import model.com.shinigami.model.Endereco;

import java.util.ArrayList;
import java.util.List;

public class ManipulaEndereco implements Crud<Endereco> {

    public ManipulaEndereco(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public ManipulaEndereco() {
        enderecos.add(new Endereco("das Flores","Bacabal","Maranhão","Brasil","96028-188","3156","Casa 4"));
        enderecos.add(new Endereco("Ceará","São Gonçalo do Amarante","Rio Grande do Norte","Brasil","70709-703","5609","Portão 5"));
        enderecos.add(new Endereco("Vinte e Três","Brasília","Distrito Federal","Brasil","68964-296","3341","Ap 4"));
    }

    private List<Endereco> enderecos = new ArrayList<>();
    @Override
    public boolean criar(Endereco endereco) {

        this.enderecos.add(endereco);

        return true;
    }

    @Override
    public void listar() throws ListaVaziaException {
        if(enderecos.isEmpty()){
            throw new ListaVaziaException();
        }

         for(int i = 0; i < enderecos.size(); i++ ) {
             System.out.print("id: "+i+" | ");
             enderecos.get(i).imprimir();

         }
    }

    @Override
    public boolean atualizar(int idx, Endereco enderecoNovo) {
        enderecos.set(idx, enderecoNovo);
        return true;
    }

    @Override
    public boolean deletar(int idx) {

        this.enderecos.remove(idx);

        return true;
    }

    public Endereco buscarEndereco(int idx){
        return enderecos.get(idx);
    }
}
