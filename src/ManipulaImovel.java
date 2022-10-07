package model;

public class ManipulaImovel implements Crud<Imovel>{
    @Override
    public boolean criar(Imovel obj) {
        return false;
    }

    @Override
    public void listar() {

    }

    @Override
    public boolean atualizar(int idx, Imovel obj) {
        return false;
    }

    @Override
    public boolean deletar(int idx) {
        return false;
    }
}
