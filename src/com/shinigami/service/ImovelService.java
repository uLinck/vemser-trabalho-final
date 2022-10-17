package model.com.shinigami.service;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.Imovel;
import model.com.shinigami.repository.ImovelRepository;

import java.sql.SQLException;
import java.util.List;

public class ImovelService implements Service<Imovel>{
    private ImovelRepository imovelRepository ;

    public ImovelService(){
        imovelRepository = new ImovelRepository();
    }

    @Override
    public boolean adicionar(Imovel imovel) throws DadoInvalidoException{
        try{
            validaQntd(imovel.getQntdQuartos());
            validaQntd(imovel.getQntdBanheiros());
            validaValorImovel(imovel.getValorMensal());
            validaValorCondominio(imovel.getCondominio());
            Imovel adicionaImovel = imovelRepository.adicionar(imovel);
            System.out.println("Imovel adicinado com sucesso! " + adicionaImovel);
            return true;

        }catch (BancoDeDadosException e){
            e.printStackTrace();
            return false;
        }catch (DadoInvalidoException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public boolean remover(Integer id) {

        try{
            boolean conseguiuRemover = imovelRepository.remover(id);
            System.out.println("Imovel removido? " + conseguiuRemover  + "| com id= " + id);
            return conseguiuRemover;
        } catch (BancoDeDadosException e) {
           e.printStackTrace();
           return false;
        }

    }

    @Override
    public void editar(Integer id, Imovel imovel) {

        try {
            boolean conseguiuEditar = imovelRepository.editar(id, imovel);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listar() {
        try {
            List<Imovel> listar = imovelRepository.listar();
            System.out.println(listar.size());
            listar.forEach(imovel ->{
                System.out.println("id: " + imovel.getIdImovel() + "| Dono: " + imovel.getDono().getNome() + "| Alugado: " + imovel.isAlugado());
            });

        }catch (BancoDeDadosException e) {
            System.out.println(e.getCause());
        }
    }

    public Imovel buscarImovel(Integer id) throws BancoDeDadosException {
        try {
            return imovelRepository.buscarImovel(id);
        } catch(BancoDeDadosException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    private boolean validaQntd(int qnt) throws DadoInvalidoException{
        if(qnt < 0){
            throw new DadoInvalidoException("Quantidade Invalida");
        }
        return true;
    }

    private boolean validaValorCondominio(double valor) throws DadoInvalidoException{
        if(valor < 0){
            throw new DadoInvalidoException("Valor Condominio Invalido");
        }
        return true;
    }


    public boolean validaValorImovel(double valor) throws DadoInvalidoException{
       if(valor <= 0) {
           throw new DadoInvalidoException("Valor do aluguel não pode ser menor ou igual a zero!");
        }
        return true;
    }
}

