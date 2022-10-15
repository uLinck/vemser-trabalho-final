//package model.com.shinigami.service;
//
//import model.com.shinigami.exceptions.ListaVaziaException;
//import model.com.shinigami.model.Cliente;
//import model.com.shinigami.model.Contrato;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ManipulaContrato implements Crud<Contrato> {
//    private List<Contrato> contratos = new ArrayList<>();
//
//    public ManipulaContrato() {
//    }
//
//    public ManipulaContrato(List<Contrato> contratos) {
//        this.contratos = contratos;
//    }
//
//    @Override
//    public boolean criar(Contrato contrato) {
//        contratos.add(contrato);
//        return false;
//    }
//
//    @Override
//    public void listar() throws ListaVaziaException {
//        if(contratos.isEmpty()){
//            throw new ListaVaziaException();
//        }
//        for(int i=0; i < contratos.size(); i++){
//            System.out.print("id: "+i+" | ");
//            System.out.println("Numero Contrato: "+contratos.get(i).getNumeroDeContrato() + " | Locatario: "+contratos.get(i).getLocatario().getNome());
//        }
//    }
//
//    @Override
//    public boolean atualizar(int idx, Contrato novoContrato) {
//        contratos.set(idx,novoContrato);
//        return true;
//    }
//
//    @Override
//    public boolean deletar(int idx) {
//        contratos.get(idx).getImovel().setAlugado(false);
//        contratos.remove(contratos.get(idx));
//        return true;
//    }
//
//    public Contrato buscarContrato(String numeroDeContrato){
//        return contratos.stream()
//                .filter(imovel -> imovel.getNumeroDeContrato().contains(numeroDeContrato))
//                .findFirst()
//                .get();
//    }
//    public Contrato buscarContrato(int idx){
//        return contratos.get(idx);
//    }
//
//
//    public List<Contrato> buscarContrato(Cliente cliente){
//        return contratos.stream()
//                .filter(contrato -> contrato.getLocador().equals(cliente) || contrato.getLocatario().equals(cliente))
//                .toList();
//    }
//}
