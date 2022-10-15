package model.com.shinigami.model;

import model.com.shinigami.model.Cliente;
import model.com.shinigami.model.Endereco;
import model.com.shinigami.model.Impressao;
import model.com.shinigami.model.TipoImovel;

public abstract class Imovel implements Impressao {

    private Endereco endereco;
    private int qntdQuartos,qntdBanheiros,idImovel;
    private double valorMensal,condominio;
    private boolean alugado;
    private TipoImovel tipoImovel;

    private Cliente dono;

    public Imovel() {}


    public Imovel(int idImovel,Endereco endereco, int qntdQuartos, int qntdBanheiros, double valorMensal, double condominio, TipoImovel tipoImovel) {
        this.endereco = endereco;
        this.qntdQuartos = qntdQuartos;
        this.qntdBanheiros = qntdBanheiros;
        this.valorMensal = valorMensal;
        this.condominio = condominio;
        this.tipoImovel = tipoImovel;
        alugado = false;
    }

    public int getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(int idImovel) {
        this.idImovel = idImovel;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public int getQntdQuartos() {
        return qntdQuartos;
    }

    public void setQntdQuartos(int qntdQuartos) {
        this.qntdQuartos = qntdQuartos;
    }

    public int getQntdBanheiros() {
        return qntdBanheiros;
    }

    public void setQntdBanheiros(int qntdBanheiros) {
        this.qntdBanheiros = qntdBanheiros;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public double getCondominio() {
        return condominio;
    }

    public void setCondominio(double condominio) {
        this.condominio = condominio;
    }

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public void imprimir() {
        System.out.println("" +
                "CEP do endereco: " + endereco.getCep() + " - " +
                "Quantidade de Quartos: " + qntdQuartos +" - " +
                "Quantidade de Banheiros: " + qntdBanheiros +" - " +
                "Valor Mensal Aluguel:" + valorMensal +" - " +
                "Taxa do Condominio: " + condominio +" - " +
                "Alugado: " + alugado +" - " +
                "Tipo do Imovel=" + tipoImovel+ " - "+"Dono: "+dono.getNome());
    }

}
