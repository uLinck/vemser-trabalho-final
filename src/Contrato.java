package model;

public class Contrato {
    private String numeroDeContrato;
    private Cliente locador;
    private Cliente locatario;
    private double valorAluguel;
    private String dataEntrada;
    private String dataVencimento;


    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getNumeroDeContrato() {
        return numeroDeContrato;
    }

    public void setNumeroDeContrato(String numeroDeContrato) {
        this.numeroDeContrato = numeroDeContrato;
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

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
}