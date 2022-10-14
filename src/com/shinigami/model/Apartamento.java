package model.com.shinigami.model;

public class Apartamento extends Imovel {
    private boolean permiteAnimais;
    private boolean salaoDeFesta;
    private int numeroDeVagas;

    public Apartamento(Endereco endereco, int qntdQuartos, int qntdBanheiros, double valorMensal, double condominio, TipoImovel tipoImovel, boolean permiteAnimais, boolean salaoDeFesta, int numeroDeVagas) {
        super(endereco, qntdQuartos, qntdBanheiros, valorMensal, condominio, tipoImovel);
        this.permiteAnimais = permiteAnimais;
        this.salaoDeFesta = salaoDeFesta;
        this.numeroDeVagas = numeroDeVagas;
    }

    public Apartamento(){

    }

    public boolean isPermiteAnimais() {
        return permiteAnimais;
    }

    public void setPermiteAnimais(boolean permiteAnimais) {
        this.permiteAnimais = permiteAnimais;
    }

    public boolean isSalaoDeFesta() {
        return salaoDeFesta;
    }

    public void setSalaoDeFesta(boolean salaoDeFesta) {
        this.salaoDeFesta = salaoDeFesta;
    }

    public int getNumeroDeVagas() {
        return numeroDeVagas;
    }

    public void setNumeroDeVagas(int numeroDeVagas) {
        this.numeroDeVagas = numeroDeVagas;
    }
}
