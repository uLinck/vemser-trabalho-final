package model.teste;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManipulaImovelTest {

    Imovel imovel;

    @BeforeEach
    public void init(){
        Endereco endereco = new  Endereco("das Flores","Bacabal","Maranhão","Brasil","96028-188","3156","Casa 4");
        Cliente c1 = new Cliente("Eusébio Rabelo Leal","54222440019","eusebio@gmail.com","(21) 3534-7699", TipoCliente.LOCADOR);
        Cliente c2 = new Cliente("Dinarte Ponte Mateus","29854982017","dinarte@gmail.com","(38) 2511-4199",TipoCliente.LOCATARIO);
        Imovel imovel = new Apartamento(endereco,2,2,1600.00,300.00,TipoImovel.APARTAMENTO,true,true,1 );;
        imovel.setDono(c1);

    }

    @Test
    void testarCriaImovel(){
        init();
        ManipulaImovel crud = new ManipulaImovel();
        crud.criar(imovel);
        assertEquals(imovel, crud.buscarImovel(0));
    }

    @Test
    void testarBuscarImovel(){
        init();
        ManipulaImovel crud = new ManipulaImovel();
        crud.criar(imovel);
        Imovel retorno = crud.buscarImovel(0);
        assertEquals(retorno, imovel);
    }


}