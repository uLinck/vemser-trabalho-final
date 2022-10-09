package model.teste;

import model.Endereco;
import model.ManipulaEndereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManipulaEnderecoTest {
    Endereco endereco;
    @BeforeEach
    void setUp() {
        endereco = new Endereco("das Flores","Bacabal","Maranhão","Brasil","96028-188","3156","Casa 4");
    }
    @Test
    void atualizar() {
        ManipulaEndereco crud = new ManipulaEndereco();
        crud.criar(endereco);
        Endereco novo = new Endereco("Ceará","São Gonçalo do Amarante","Rio Grande do Norte","Brasil","70709-703","5609","Portão 5");
        assertTrue(crud.atualizar(0,novo));
    }

    @Test
    void deletar() {
        ManipulaEndereco crud = new ManipulaEndereco();
        crud.criar(endereco);
        assertTrue(crud.deletar(0));
    }
}