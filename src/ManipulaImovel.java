package model;

import java.util.ArrayList;
import java.util.List;

public class ManipulaImovel implements Crud<Imovel>{
    List<Imovel> imoveis = new ArrayList<>();
    @Override
    public boolean criar(Imovel obj) {
        imoveis.add(obj);
        return true;
    }

    @Override
    public void listar() {
        imoveis.stream()
                .forEach(imovel -> {
                    System.out.println(imovel);
                });
    }

    @Override
    public boolean atualizar(int idx, Imovel obj) {
        imoveis.set(idx,obj);
        return false;
    }

    @Override
    public boolean deletar(int idx) {
        imoveis.remove(imoveis.get(idx));
        return false;
    }
}
