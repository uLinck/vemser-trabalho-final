package model.com.shinigami.testes;

import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.Cliente;
import model.com.shinigami.service.ClienteService;
import model.com.shinigami.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    ClienteService cService;
    @BeforeEach
    public void init(){
        cService = new ClienteService();
    }

    @Test
    public void testValidaEmailCorreto(){
        try{
            Cliente c = new Cliente();
            c.setEmail("lucas@gmail.com");
            assertTrue(cService.validaEmail(c));
        }catch (DadoInvalidoException e){
        }
    }

    @Test
    public void testValidaEmailErrado(){
        Cliente c = new Cliente();
        c.setEmail("aa");
        Exception e = assertThrows(DadoInvalidoException.class, () ->{
            cService.validaEmail(c);
        });
        String expected = "Email invalido!";
        String actual = e.getMessage();
        assertTrue(actual.contains(expected));
    }
    @Test
    public void testeValidaCPFCorreto(){
        try{
            Cliente c = new Cliente();
            c.setCpf("12345678-01");
            cService.validaCpf(c);
        }catch (DadoInvalidoException e){
        }
    }

    @Test
    public void testeValidaCPFErrado(){
        Cliente c = new Cliente();
        c.setCpf("123");
        Exception e = assertThrows(DadoInvalidoException.class, () ->{
            cService.validaCpf(c);
        });
        String expected = "CPF Invalido!";
        String actual = e.getMessage();
        assertTrue(actual.contains(expected));
    }

}