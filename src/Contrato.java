package model;

public class Contrato implements Impressao {
    private String numeroDeContrato;
    private Cliente locador;
    private Cliente locatario;
    private double valorAluguel;
    private String dataEntrada;
    private String dataVencimento;

    public Contrato(String numeroDeContrato, Cliente locador, Cliente locatario, double valorAluguel, String dataEntrada, String dataVencimento) {
        this.numeroDeContrato = numeroDeContrato;
        this.locador = locador;
        this.locatario = locatario;
        this.valorAluguel = valorAluguel;
        this.dataEntrada = dataEntrada;
        this.dataVencimento = dataVencimento;
    }

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

    @Override
    public void imprimir() {
        System.out.println("" +
                "numeroDeContrato: " + numeroDeContrato + " - " +
                "Locador: " + "Nome Locador: "+ locador.getNome() +
                            " CPF Locador:"+ locador.getCpf() +" - " +
                "Locatario: " + "Nome Locatario: "+locatario.getNome() +
                                " CPF Locatario: "+ locatario.getCpf() + " - " +
                "Valor do Aluguel: " + valorAluguel + " - " +
                "Data de Entrada: " + dataEntrada + " - " +
                "Data de Vencimento: " + dataVencimento + " - ");
    }
}
