package model.com.shinigami.model;

public class Endereco implements Impressao {
    private String rua,cidade,estado,pais,cep,complemento;
    private int idEndereco, numero;
    public Endereco(int idEndereco,String rua, String cidade, String estado, String pais, String cep, int numero, String complemento) {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Endereco() {
    }
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public void imprimir() {
        System.out.println("País: " + pais + " - " +
                "Estado: " + estado + " - " +
                "Cidade: " + cidade + " - " +
                "Rua: " + rua + " - " +
                "Número: " + numero + " - " +
                "Complemento: " + complemento + " - "+
                "CEP: " + cep);
    }
}
