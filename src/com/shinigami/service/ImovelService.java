package model.com.shinigami.service;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.Apartamento;
import model.com.shinigami.model.Imovel;
import model.com.shinigami.model.TipoImovel;
import model.com.shinigami.repository.ImovelRepository;

import java.sql.SQLException;
import java.util.List;

public class ImovelService {
    private ImovelRepository imovelRepository ;

    public ImovelService(){
        imovelRepository = new ImovelRepository();
    }

    public boolean adicionarImovel(Imovel imovel) throws BancoDeDadosException{
        try{
            validaValorImovel(imovel);
            Imovel adicionaImovel = imovelRepository.adicionar(imovel);
            System.out.println("Imovel adicinado com sucesso! " + adicionaImovel);
            return true;

        }catch (BancoDeDadosException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean removerImovel(Integer id) {

        try{
            boolean conseguiuRemover = imovelRepository.remover(id);
            System.out.println("Imovel removido? " + conseguiuRemover  + "| com id= " + id);
            return conseguiuRemover;
        } catch (BancoDeDadosException e) {
           e.printStackTrace();
           return false;
        }

    }
    public void editarImovel(Integer id, Imovel imovel) {

        try {
            boolean conseguiuEditar = imovelRepository.editar(id, imovel);
            System.out.println("Imovel editado? " + conseguiuEditar + "| com id= " + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarImovel() {
        try {
            List<Imovel> listar = imovelRepository.listar();
            listar.forEach(imovel ->{
                System.out.println("id: " + imovel.getIdImovel() + "| Dono: " + imovel.getDono().getNome() + "| Alugado: " + imovel.isAlugado());
            });

        }catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Imovel buscarImovel(Integer id) throws BancoDeDadosException {
        try {
            return imovelRepository.buscarImovel(id);
        } catch(BancoDeDadosException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    public boolean validaImovel(Imovel imovel) throws DadoInvalidoException {

         if ((Integer)imovel.getDono().getIdCliente() != null) {
            throw new DadoInvalidoException();
        } else if(imovel.getTipoImovel() != null) {
            throw new DadoInvalidoException();
        } else if((Integer)imovel.getQntdQuartos() != null) {
            throw  new DadoInvalidoException();
        } else if((Integer)imovel.getQntdBanheiros() != null) {
            throw new DadoInvalidoException();
        } else if(imovel.getEndereco() != null) {
            throw new DadoInvalidoException();
        } else if((Double)imovel.getValorMensal() != null) {
            throw new DadoInvalidoException();
        } else if((Double)imovel.getCondominio() != null) {
            throw new DadoInvalidoException();
        }

        if(imovel.getTipoImovel() == TipoImovel.APARTAMENTO) {
           if((Boolean)((Apartamento)imovel).isPermiteAnimais() != null) {
               throw new DadoInvalidoException(); // blow up
           }
        }
        return true;
    }
    public boolean validaValorImovel(Imovel imovel){
       if(imovel.getValorMensal() <= 0) {
           return false;
        } else if(imovel.getValorMensal() + imovel.getCondominio() <= 0){
            return false;
        } else if(imovel.getCondominio() < 0) {
           return false;
       }
        return true;
    }
}

