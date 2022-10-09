package model;
public interface Crud<T> {
    boolean criar(T obj);
    void listar() throws ListaVaziaException;
    boolean atualizar(int idx,T obj);
    boolean deletar(int idx);
}
