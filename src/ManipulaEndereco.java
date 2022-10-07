package model;

import java.util.ArrayList;
import java.util.List;

public class ManipulaEndereco implements Crud<Endereco>{

    List<Endereco> enderecos = new ArrayList<>();
    @Override
    public boolean criar(Endereco obj) {

        this.enderecos.add(obj);

        return true;
    }

    @Override
    public void listar() {

     for(int i = 0; i < enderecos.size(); i++ ) {

         System.out.println("Endereço id=" + i + " | " + enderecos.get(i));

     }

    }

    @Override
    public boolean atualizar(int idx, Endereco obj) {

        Endereco atualizarEndereco = enderecos.get(idx);

        atualizarEndereco.setCep(obj.getCep());
        atualizarEndereco.setRua(obj.getRua());
        atualizarEndereco.setPais(obj.getPais());
        atualizarEndereco.setComplemento(obj.getComplemento());
        atualizarEndereco.setEstado(obj.getEstado());
        atualizarEndereco.setCidade(obj.getCidade());
        atualizarEndereco.setNumero(obj.getNumero());

        return true;
    }

    @Override
    public boolean deletar(int idx) {

        this.enderecos.remove(idx);

        return true;
    }
}
