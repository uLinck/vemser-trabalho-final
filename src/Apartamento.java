package model;

public class Apartamento extends Imovel {
    private boolean permiteAnimais;
    private boolean salaoDeFesta;
    private int numeroDeVagas;

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
