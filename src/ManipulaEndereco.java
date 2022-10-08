package model;

import java.util.ArrayList;
import java.util.List;

public class ManipulaEndereco implements Crud<Endereco>{

    List<Endereco> enderecos = new ArrayList<>();
    @Override
    public boolean criar(Endereco endereco) {

        this.enderecos.add(endereco);

        return true;
    }

    @Override
    public void listar() {

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
}
