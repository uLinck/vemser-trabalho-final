package model.com.shinigami.service;

import model.com.shinigami.exceptions.DadoInvalidoException;

public interface Service<T> {
    boolean adicionar(T obj) throws DadoInvalidoException;
    void listar();
    void editar(Integer idx,T obj) throws DadoInvalidoException;
    boolean remover(Integer idx) throws DadoInvalidoException;
}
