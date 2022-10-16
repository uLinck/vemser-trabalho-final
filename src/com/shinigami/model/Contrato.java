package model.com.shinigami.model;

import java.time.LocalDate;

public class Contrato implements Impressao {
    private Cliente locador,locatario;
    private double valorAluguel;
    private LocalDate dataEntrada,dataVencimento;
    private Imovel imovel;
    private int idContrato;
    private boolean ativo;

    public Contrato() {

    }

    public Contrato(int idContrato, Cliente locador, Cliente locatario, double valorAluguel, LocalDate dataEntrada, LocalDate dataVencimento, Imovel imovel) {
        this.locador = locador;
        this.locatario = locatario;
        this.valorAluguel = valorAluguel;
        this.dataEntrada = dataEntrada;
        this.dataVencimento = dataVencimento;
        this.imovel = imovel;
        this.idContrato = idContrato;
    }


    public Cliente getLocador() {
        return locador;
    }

    public void setLocador(Cliente locador) {
        this.locador = locador;
    }

    public Cliente getLocatario() {
        return locatario;
    }

    public void setLocatario(Cliente locatario) {
        this.locatario = locatario;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public void imprimir() {
        System.out.print("" +
                "Numero Do Contrato: " + idContrato + " \n " +
                "Locador: " + "Nome Locador: "+ locador.getNome() +
                " - CPF Locador: "+ locador.getCpf() +" \n " +
                "Locatario: " + "Nome Locatario: "+locatario.getNome() +
                " - CPF Locatario: "+ locatario.getCpf() + " \n " +
                "Valor do Aluguel: " + valorAluguel + " - " +
                "Data de Entrada: " + dataEntrada + " - " +
                "Data de Vencimento: " + dataVencimento + " - \n"+
                " Endereço: ");
            imovel.getEndereco().imprimir();
    }
}
