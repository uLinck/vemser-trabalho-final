package model.com.shinigami.testes;

import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.Cliente;
import model.com.shinigami.service.ImovelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImovelServiceTest {
    ImovelService iService;

    @BeforeEach
    public void init() {
        iService = new ImovelService();
    }

    @Test
    public void testValidaValorImovelCorreto() {
        try {
            assertTrue(iService.validaValorImovel(100));
        }catch (DadoInvalidoException e) {
        }
    }

    @Test
    public void testValidaValorImovelErrado(){
        Exception e = assertThrows(DadoInvalidoException.class, () ->{
            iService.validaValorImovel(-100);
        });
        String expected = "Valor do aluguel não pode ser menor ou igual a zero!";
        String actual = e.getMessage();
        assertTrue(actual.contains(expected));
    }

}
