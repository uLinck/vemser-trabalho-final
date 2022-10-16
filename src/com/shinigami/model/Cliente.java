package model.com.shinigami.model;

public class Cliente implements Impressao {

    private String nome, cpf, email, telefone;
    private int idCliente;
    private boolean ativo;
    private TipoCliente tipoCliente;
    public Cliente() {
    }

    public Cliente(int idCliente,String nome, String cpf, String email,String telefone,TipoCliente tipoCliente) {
        this.idCliente = idCliente;
        this.telefone = telefone;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.tipoCliente = tipoCliente;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public void imprimir() {
        System.out.println("Id: " + idCliente +
                "Nome: " + nome + " - " +
                "CPF: " + cpf + " - " +
                "Email: " + email + " - " +
                "Telefone: " + telefone + " - " +
                "Tipo Cliente: " + tipoCliente.toString());
    }

}
