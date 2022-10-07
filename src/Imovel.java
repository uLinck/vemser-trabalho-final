package model;

public class Imovel {

    private Endereco endereco;
    private int qntdQuartos,qntdBanheiros;
    private double valorMensal,condominio;
    private boolean alugado;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
