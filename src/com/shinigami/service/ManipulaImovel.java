package model.com.shinigami.service;

import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.exceptions.ListaVaziaException;
import model.com.shinigami.model.Imovel;

import java.util.ArrayList;
import java.util.List;

public class ManipulaImovel implements Crud<Imovel> {
    private List<Imovel> imoveis = new ArrayList<>();

    public ManipulaImovel(List<Imovel> imoveis) {
        this.imoveis = imoveis;
    }
    public ManipulaImovel() {
    }

    @Override
    public boolean criar(Imovel imovel) {
        imoveis.add(imovel);
        return true;
    }

    @Override
    public void listar() throws ListaVaziaException {
        if(imoveis.isEmpty()){
            throw new ListaVaziaException();
        }
        for(int i=0; i < imoveis.size(); i++){
            System.out.print("id: "+i+" | ");
            System.out.println(imoveis.get(i).getTipoImovel()+" - "+imoveis.get(i).getValorMensal()+" - Alugado: "+imoveis.get(i).isAlugado());
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


    public void listaImovelDisponivel()throws DadoInvalidoException {
        int i = 0;
        List<Imovel> listar =imoveis.stream()
                .filter(imovel -> imovel.isAlugado() == false)
                .toList();
        if(listar.isEmpty()){
            throw new DadoInvalidoException("Nenhum Imovel Disponivel");
        }
        for(Imovel imovel:listar){
            System.out.print("id: "+i+" | ");
            listar.get(i).imprimir();
            i++;
        }
    }

    public Imovel buscarImovelDisponivel(int idx){
        return imoveis.stream()
                .filter(imovel -> imovel.isAlugado() == false)
                .toList()
                .get(idx);
    }
}
