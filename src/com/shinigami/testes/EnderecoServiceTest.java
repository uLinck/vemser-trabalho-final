package model.com.shinigami.testes;

import model.com.shinigami.service.EnderecoService;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoServiceTest {
    EnderecoService eService;
    @BeforeAll
    public void init(){
        eService = new EnderecoService();
    }



}