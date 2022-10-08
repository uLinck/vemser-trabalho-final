package model;

public class Cliente implements Impressao{
    public Cliente() {
    }

    public Cliente(String nome, String cpf, String email,String telefone,TipoCliente tipoCliente) {
        this.telefone = telefone;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.tipoCliente = tipoCliente;
    }

    String nome, cpf, email, telefone;
    TipoCliente tipoCliente;

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
        System.out.println("" +
                "Nome: " + nome + " - " +
                "CPF: " + cpf + " - " +
                "Email: " + email + " - " +
                "Telefone: " + telefone + " - " +
                "Tipo Cliente: " + tipoCliente);
    }

}
