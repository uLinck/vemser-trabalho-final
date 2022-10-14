package model.com.shinigami.teste;

import model.com.shinigami.model.Cliente;
import model.com.shinigami.model.TipoCliente;
import model.com.shinigami.service.ManipulaCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManipulaClienteTest {

    Cliente c1;
    Cliente c2;

@BeforeEach
    public void init() {

    c1 = new Cliente("Eusébio Rabelo Leal","54222440019","eusebio@gmail.com","(21) 3534-7699", TipoCliente.LOCADOR);
    c2 = new Cliente("Dinarte Ponte Mateus","29854982017","dinarte@gmail.com","(38) 2511-4199",TipoCliente.LOCATARIO);
    }

    @Test
    void testeCriarCliente() {

        ManipulaCliente crud = new ManipulaCliente();

        crud.criar(c1);
        assertEquals(c1.getNome(), crud.buscarCliente(0).getNome());

    }

    @Test
    void testarAtualizarCliente() {
        ManipulaCliente crud = new ManipulaCliente();
        crud.criar(c1);
        Cliente cliente = crud.buscarCliente(0);
        cliente.setNome("Rafael");

        crud.atualizar(0, cliente);
        assertEquals("Rafael", crud.buscarCliente(0).getNome());

    }


}