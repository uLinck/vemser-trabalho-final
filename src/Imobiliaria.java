package model;

import java.util.Scanner;

public class Imobiliaria {
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String ROXO = "\u001B[35m";
    public static final String AMARELO = "\u001B[33m";
    public static final String RESET = "\u001B[0m";

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
                switch(opcao){
                    case 1 -> {
                        opcao = menuCliente(linha);
                        switch (opcao){
                            case 1 ->{
                                crudCliente.criar(pegarInformaçõesCliente(linha));
                                System.out.println("Cliente cadastrado com Sucesso!");
                            }
                            case 2 ->{
                                System.out.println("\nQual usuario você deseja atualizar?");
                                crudCliente.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                crudCliente.buscarCliente(opcao);
                                crudCliente.atualizar(opcao,pegarInformaçõesCliente(linha));
                                System.out.println("Atualizado com Sucesso!");
                            }
                            case 3 -> {
                                crudCliente.listar();
                            }case 4 -> {
                                System.out.println("\nQual usuario você deseja Remover?");
                                crudCliente.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                System.err.print("\nTem certeza que deseja remover o cliente: 'Sim' ou 'Nao' "+opcao+"\n");
                                crudCliente.buscarCliente(opcao).imprimir();
                                if(linha.nextLine().toLowerCase().equals("sim")){
                                    crudCliente.deletar(opcao);
                                    System.err.println("Deletado com Sucesso!");
                                }else {
                                    System.err.println("Remover Cancelado!");

                                }

                            }case 5 -> {
                                //FALTA FINALIZAR
                                System.out.println("Qual cliente você deseja buscar informações");
                                crudCliente.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();

                            }default -> {
                                throw new OpcaoInvalidaException();
                            }
                        }

                    }case 2->{//MENU IMOVEL


                    }case 3 ->{//MENU CONTRATOS

                    } default -> {
                        throw new OpcaoInvalidaException();
                    }
                }

            }catch (OpcaoInvalidaException e){
                System.err.println("Opcao Invalida! Tente Novamente\n");
            }catch (DadoInvalidoException e){
                System.err.println("Dados Invalidos! Tente Novamente\n");
            }catch (IndexOutOfBoundsException e){
                System.err.println("Não encontrado! Tente Novamente");
            }



        }
    }

    private static int menuInicial(Scanner linha) throws OpcaoInvalidaException{
        System.out.println(AMARELO +"Imobiliaria Shinigamis"+RESET);
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println(AZUL+"1- Menu Cliente"+RESET);
        System.out.println(ROXO+"2- Menu Imovel"+RESET);
        System.out.println(VERDE+"3- Menu Contratos"+RESET);
        System.out.println(VERMELHO+"9- SAIR"+RESET);
        int opcao = linha.nextInt();
        linha.nextLine();
        return opcao;

    }
    private static int menuCliente(Scanner linha) throws OpcaoInvalidaException{

        System.out.println(AMARELO +"Imobiliaria Shinigamis"+RESET);
        System.out.println("Escolha uma das opções do Menu Cliente: ");
        System.out.println(AZUL+"1- Cadastrar Cliente");
        System.out.println("2- Editar Cliente");
        System.out.println("3- Listar Clientes");
        System.out.println("4- Remover Cliente");
        System.out.println("5- Buscar Cliente");
        System.out.println(VERMELHO+"9- SAIR"+RESET);
        int opcao = linha.nextInt();
        linha.nextLine();
        return opcao;
    }

    private static Cliente pegarInformaçõesCliente(Scanner linha) throws DadoInvalidoException{
        System.out.println(AZUL+"\nInforme o nome do cliente");
        String nome = linha.nextLine();
        System.out.println("\nInforme o cpf do cliente");
        String cpf = linha.nextLine();
        System.out.println("\nInforme o email do cliente");
        String email = linha.nextLine();
        System.out.println("\nInforme o telefone do cliente");
        String telefone = linha.nextLine();
        System.out.println("\nInforme se o cliente sera Locador(1) ou Locatario(2)"+RESET);
        int tipo = linha.nextInt();
        linha.nextLine();
        if(nome.isBlank() || cpf.isBlank() || email.isBlank() || telefone.isBlank() || (tipo != 1 && tipo !=2)){
            throw new DadoInvalidoException();
        }
        if(tipo == 1){
            return new Cliente(nome,cpf,email,telefone,TipoCliente.LOCADOR);
        }
        return new Cliente(nome,cpf,email,telefone,TipoCliente.LOCATARIO);
    }



}