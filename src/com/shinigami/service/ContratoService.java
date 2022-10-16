package model.com.shinigami.service;


import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.Contrato;
import model.com.shinigami.repository.ContratoRepository;

import java.time.LocalDate;
import java.util.List;

public class ContratoService implements Service<Contrato>{

    private ContratoRepository contratoRepository;

    public ContratoService() {
        contratoRepository = new ContratoRepository();
    }

    @Override
    public boolean adicionar(Contrato contrato)throws DadoInvalidoException {
        try {
            validaDataVencimento(contrato.getDataEntrada(),contrato.getDataVencimento());
            Contrato contratoAdicionado = contratoRepository.adicionar(contrato);
            System.out.println("cliente adicinada com sucesso! " + contratoAdicionado);
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return false;
        } catch (DadoInvalidoException e){
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remover(Integer id) throws DadoInvalidoException{
        try {
            boolean conseguiuRemover = contratoRepository.remover(id);
            System.out.println("contrato removido? " + conseguiuRemover + "| com id=" + id);
            return conseguiuRemover;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void editar(Integer id, Contrato contrato) throws DadoInvalidoException {
        try {
            boolean conseguiuEditar = contratoRepository.editar(id, contrato);
            System.out.println("contrato editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listar() {
        try {
            List<Contrato> listar = contratoRepository.listar();
            listar.forEach(contrato ->{
                System.out.println("id:"+contrato.getIdContrato()+" | Nome Locatario: "+contrato.getLocatario().getNome()+" | Nome Locador: "+contrato.getLocador().getNome());
            });
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Contrato buscarContrato(int id)throws BancoDeDadosException{
        try {
            return contratoRepository.buscarContrato(id);
        }catch (BancoDeDadosException e){
            throw new BancoDeDadosException(e.getCause());
        }
    }

    private boolean validaDataVencimento(LocalDate dataEntrada, LocalDate dataVencimento) throws DadoInvalidoException{
        if(dataVencimento.isBefore(dataEntrada)){
            throw new DadoInvalidoException("Data Vencimento Invalida!");
        }
        return true;
    }





}