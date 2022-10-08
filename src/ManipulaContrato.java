package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManipulaContrato implements Crud<Contrato>{
    List<Contrato> contratos = new ArrayList<>();

    @Override
    public boolean criar(Contrato contrato) {
        contratos.add(contrato);
        return false;
    }

    @Override
    public void listar() {
        contratos.stream()
                .forEach(contrato -> {
                    System.out.println(contrato);
                });
    }

    @Override
    public boolean atualizar(int idx, Contrato novoContrato) {
        contratos.set(idx,novoContrato);
        return false;
    }

    @Override
    public boolean deletar(int idx) {
        contratos.remove(contratos.get(idx));
        return false;
    }

    public Contrato buscarContrato(String numeroDeContrato){
        return contratos.stream()
                .filter(imovel -> imovel.getNumeroDeContrato().contains(numeroDeContrato))
                .findFirst()
                .get();
    }

    public List<Contrato> buscarContrato(Cliente cliente){
        return contratos.stream()
                .filter(contrato -> contrato.getLocador().equals(cliente) || contrato.getLocatario().equals(cliente))
                .toList();
    }
}
