package model.com.shinigami.testes;

import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.Cliente;
import model.com.shinigami.service.ContratoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ContratoServiceTest {
    ContratoService cService;
    @BeforeEach
    public void init(){
        cService = new ContratoService();
    }

    @Test
    public void testValidaDataVencimentoErrado(){
        Exception e = assertThrows(DadoInvalidoException.class, () ->{
            LocalDate ld = LocalDate.parse("10/10/2020", DateTimeFormatter.ofPattern("d/MM/yyyy"));
            LocalDate ldv = LocalDate.parse("10/10/2019", DateTimeFormatter.ofPattern("d/MM/yyyy"));
            cService.validaDataVencimento(ld,ldv);
        });
        String expected = "Data Vencimento Invalida!";
        String actual = e.getMessage();
        assertTrue(actual.contains(expected));
    }

    @Test
    public void testValidaDataVencimentoCorreta(){
        try {
            LocalDate ld = LocalDate.parse("10/10/2020", DateTimeFormatter.ofPattern("d/MM/yyyy"));
            LocalDate ldv = LocalDate.parse("10/10/2021", DateTimeFormatter.ofPattern("d/MM/yyyy"));
            assertTrue(cService.validaDataVencimento(ld, ldv));
        }catch (DadoInvalidoException e){

        }
    }
}