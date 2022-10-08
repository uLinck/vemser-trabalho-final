package model;

import java.util.ArrayList;
import java.util.List;

public class ManipulaImovel implements Crud<Imovel> {
    List<Imovel> imoveis = new ArrayList<>();

    @Override
    public boolean criar(Imovel imovel) {
        imoveis.add(imovel);
        return true;
    }

    @Override
    public void listar() {
        for(int i=0; i < imoveis.size(); i++){
            System.out.print("id: "+i+" | ");
            imoveis.get(i).imprimir();
        }
    }

    @Override
    public boolean atualizar(int idx, Imovel imovelNovo) {
        imoveis.set(idx, imovelNovo);
        return true;
    }

    @Override
    public boolean deletar(int idx) {
        imoveis.remove(imoveis.get(idx));
        return true;
    }

    public Imovel buscarImovel(int idx) {
        return imoveis.get(idx);
    }

    public void listarApartamentos() {
        imoveis.stream()
                .filter(imovel -> imovel.getTipoImovel().equals(TipoImovel.APARTAMENTO))
                .forEach(imovel -> System.out.println(imovel));
    }

    public void listarCasas() {
        imoveis.stream()
                .filter(imovel -> imovel.getTipoImovel().equals(TipoImovel.CASA))
                .forEach(imovel -> System.out.println(imovel));
    }
}
