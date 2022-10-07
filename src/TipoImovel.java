package model;

public enum TipoImovel  {
    CASA("Casa"),
    APARTAMENTO("Apartamento");

    private String descricao;

    TipoImovel(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
