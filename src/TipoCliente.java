package model;

public enum TipoCliente {
    LOCADOR("Locador"),
    LOCATARIO("Locatario");

    private String descricao;

    TipoCliente(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
