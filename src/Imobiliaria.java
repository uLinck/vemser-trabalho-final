package model;

import java.util.Scanner;

public class Imobiliaria {

    public static void main(String[] args) {
        Scanner linha = new Scanner(System.in);
        ManipulaCliente crudCliente = new ManipulaCliente();
        ManipulaEndereco crudEndereco = new ManipulaEndereco();
        ManipulaImovel crudImovel = new ManipulaImovel();
        ManipulaContrato crudContrato = new ManipulaContrato();
        boolean execucao = true;
        while(execucao){
            try{
                int opcao = menuInicial(linha);
                linha.nextLine();
                switch(opcao){
                    case 1 -> {
                        opcao = menuCliente(linha);
                        linha.nextLine();
                        switch (opcao){
                            case 1 ->{
                                crudCliente.criar(pegarInformaçõesCliente(linha));
                                System.out.println("Cliente cadastrado com Sucesso!");
                            }
                            case 2 ->{
                                System.out.println("Qual usuario você deseja atualizar?");
                                crudCliente.listar();
                                crudCliente.atualizar(1,pegarInformaçõesCliente(linha));
                            }
                        }

                    } default -> {

                    }
                }

            }catch (OpcaoInvalidaException e){
                System.err.println("Opcao Invalida! Tente Novamente\n");
            }catch (DadoInvalidoException e){
                System.err.println("Dados Invalidos! Tente Novamente\n");
            }



        }
    }

    private static int menuInicial(Scanner linha) throws OpcaoInvalidaException{
        System.out.println("Imobiliaria Shinigamis");
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println("1- Menu Cliente");
        System.out.println("2- Menu Imovel");
        System.out.println("3- Menu Contratos");
        System.err.println("9- SAIR");
        return linha.nextInt();

    }
    private static int menuCliente(Scanner linha) throws OpcaoInvalidaException{
        System.out.println("Imobiliaria Shinigamis");
        System.out.println("Escolha uma das opções do Menu Cliente: ");
        System.out.println("1- Cadastrar Cliente");
        System.out.println("2- Editar Cliente");
        System.out.println("3- Listar Clientes");
        System.out.println("4- Remover Cliente");
        System.out.println("5- Buscar Cliente");
        System.err.println("9- SAIR");
        return linha.nextInt();
    }

    private static Cliente pegarInformaçõesCliente(Scanner linha) throws DadoInvalidoException{
        System.out.println("Informe o nome do cliente");
        String nome = linha.nextLine();
        System.out.println("Informe o cpf do cliente");
        String cpf = linha.nextLine();
        System.out.println("Informe o email do cliente");
        String email = linha.nextLine();
        System.out.println("Informe o telefone do cliente");
        String telefone = linha.nextLine();
        System.out.println("Informe se o cliente sera Locador(1) ou Locatario(2)");
        int tipo = linha.nextInt();
        linha.nextLine();
        if(nome.isBlank() || cpf.isBlank() || email.isBlank() || telefone.isBlank() || tipo != 1 || tipo !=2){
            throw new DadoInvalidoException();
        }
        if(tipo == 1){
            return new Cliente(nome,cpf,email,telefone,TipoCliente.LOCADOR);
        }
        return new Cliente(nome,cpf,email,telefone,TipoCliente.LOCATARIO);
    }



}