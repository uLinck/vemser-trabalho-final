package model.teste;

import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManipulaContratoTest {
    Contrato contrato;

    public void init(){
        Endereco endereco = new  Endereco("das Flores","Bacabal","Maranhão","Brasil","96028-188","3156","Casa 4");
        Imovel imovel = new Apartamento(endereco,2,2,1600.00,300.00,TipoImovel.APARTAMENTO,true,true,1 );
        Cliente c1 = new Cliente("Eusébio Rabelo Leal","54222440019","eusebio@gmail.com","(21) 3534-7699",TipoCliente.LOCADOR);
        Cliente c2 = new Cliente("Dinarte Ponte Mateus","29854982017","dinarte@gmail.com","(38) 2511-4199",TipoCliente.LOCATARIO);
        imovel.setDono(c1);
        contrato = new Contrato("001",imovel,c2,1600.00,"10/10/2020","10/04/2020");
    }
    @Test
    void testarCriacaoNovoContrato() {
        init();
        ManipulaContrato crud = new ManipulaContrato();
        crud.criar(contrato);
        assertEquals(contrato,crud.buscarContrato(0));
    }

    @Test
    void testarAtualizacaoContrato() {
        init();
        ManipulaContrato crud = new ManipulaContrato();
        crud.criar(contrato);
        Contrato novo = crud.buscarContrato(0);
        novo.setLocatario(new Cliente("Pablo Roriz Granja","77339166076","pablo@gmail.com","(69) 2318-9545",TipoCliente.LOCATARIO));
        crud.atualizar(0,novo);
        assertEquals("Pablo Roriz Granja",crud.buscarContrato(0).getLocatario().getNome());

    }

    @Test
    void testarDeletarContrato() {
        init();
        ManipulaContrato crud = new ManipulaContrato();
        crud.criar(contrato);
        assertTrue(crud.deletar(0));
    }


}