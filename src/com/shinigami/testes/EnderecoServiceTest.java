package model.com.shinigami.testes;

import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.service.EnderecoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoServiceTest {
    EnderecoService eService;
    @BeforeEach
    public void init(){
        eService = new EnderecoService();
    }

    @Test
    public void testeValidarRuaCorreta(){
        try{
            assertTrue(eService.validaRua("Rua Estados Unidos"));
        }catch(DadoInvalidoException e){
        }
    }
    @Test
    public void testeValidarRuaErrada(){
        Exception e = assertThrows(DadoInvalidoException.class, () ->{
            eService.validaRua("");
        });
        String expected = "Rua Invalida";
        String actual = e.getMessage();
        assertTrue(actual.contains(expected));
    }



}