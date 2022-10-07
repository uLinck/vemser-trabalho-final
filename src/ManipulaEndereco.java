package model;

public class ManipulaEndereco implements Crud<Endereco>{
    @Override
    public boolean criar(Endereco obj) {
        return false;
    }

    @Override
    public void listar() {

    }

    @Override
    public boolean atualizar(int idx, Endereco obj) {
        return false;
    }

    @Override
    public boolean deletar(int idx) {
        return false;
    }
}
