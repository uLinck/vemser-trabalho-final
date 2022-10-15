package model.com.shinigami.service;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.model.Imovel;
import model.com.shinigami.repository.ImovelRepository;

import java.sql.SQLException;

public class ImovelService {
    private ImovelRepository imovelRepository ;

    public ImovelService(){
        imovelRepository = new ImovelRepository();
    }

    public boolean adicionarImovel(Imovel imovel) throws BancoDeDadosException{
        try{
            validaValorImovel(imovel);
            Imovel adicionaImovel = imovelRepository.adicionar(imovel);
            System.out.println("contato adicinado com sucesso! " + adicionaImovel);
            return true;

        }catch (BancoDeDadosException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean validaValorImovel(Imovel imovel){
        if(imovel.getValorMensal() + imovel.getCondominio() <= 0){
            return false;
        }
        return true;
    }


}
