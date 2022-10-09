package model;

import java.util.Scanner;

public class Imobiliaria {
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String ROXO = "\u001B[35m";
    public static final String AMARELO = "\u001B[33m";
    public static final String RESET = "\u001B[0m";

    static Scanner linha = new Scanner(System.in);
    static ManipulaCliente crudCliente = new ManipulaCliente();
    static ManipulaEndereco crudEndereco = new ManipulaEndereco();
    static ManipulaImovel crudImovel = new ManipulaImovel();
    static ManipulaContrato crudContrato = new ManipulaContrato();

    public static void main(String[] args) {
        boolean execucao = true;
        while(execucao){
            try{
                int opcao = menuInicial();
                switch(opcao){
                    case 1 -> {
                        opcao = menuCliente(linha);
                        switch (opcao){
                            case 1 ->{
                                crudCliente.criar(pegarInformacoesCliente());
                                System.out.println("Cliente cadastrado com Sucesso!");
                            }
                            case 2 ->{
                                System.out.println("\nQual usuario você deseja atualizar?");
                                crudCliente.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                crudCliente.buscarCliente(opcao);
                                crudCliente.atualizar(opcao, pegarInformacoesCliente());
                                System.out.println("Atualizado com Sucesso!");
                            }
                            case 3 -> {
                                crudCliente.listar();
                            }
                            case 4 -> {
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

                            }
                            case 5 -> {
                                //FALTA FINALIZAR
                                System.out.println("Qual cliente você deseja buscar informações");
                                crudCliente.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();

                            }default -> {
                                throw new OpcaoInvalidaException();
                            }
                        }
                    }
                    case 2 -> {//MENU IMOVEL
                        opcao = menuImoveis(linha);
                        switch (opcao) {

                            case 1 -> {
                                var imovel = pegarInformacoesImoveis();
                                if (imovel != null) {
                                    crudImovel.criar(imovel);
                                } else {
                                    System.out.println("Imovel nã oencontrado! Tente novamente!");
                                }
                            }

                            case 2 -> {
                                System.out.println("\nQual imóvel você deseja atualizar?");
                                crudImovel.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                crudImovel.atualizar(opcao, pegarInformacoesImoveis());
                                System.out.println("Atualizado com Sucesso!");
                            }
                            case 3 -> {
                                crudImovel.listar();
                            }
                            case 4 -> {
                                System.out.println("\nQual imóvel você deseja Remover?");
                                crudImovel.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                System.err.print("\nTem certeza que deseja remover o imóvel? (Sim ou Não)\n");
                                if(linha.nextLine().equalsIgnoreCase("sim")){
                                    crudImovel.deletar(opcao);
                                    System.err.println("Deletado com Sucesso!");
                                }else {
                                    System.err.println("Remover Cancelado!");

                                }
                            }
                            case 5 -> {
                                //FAZER BUSCAR IMOVEL, IREI FAZER DEPOIS(GUSTAVO)
                            }
                        }

                    }
                    case 3 -> {//MENU CONTRATOS
                        opcao = menuContrato(linha);

                        switch (opcao){
                            case 1 -> {
                                crudContrato.criar(pegarInformacoesContrato());
                                System.out.println("Contrato criado com sucesso!");
                            }
                            case 2 -> {
                                System.out.println("\nQual contrato você deseja atualizar?");
                                crudContrato.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                crudContrato.buscarContrato(opcao);
                                crudContrato.atualizar(opcao, pegarInformacoesContrato());
                                System.out.println("Atualizado com Sucesso!");
                            }
                            case 3 -> {
                                crudContrato.listar();
                            }
                            case 4 -> {
                                System.out.println("\nQual contrato você deseja Remover?");
                                crudContrato.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                System.err.print("\nTem certeza que deseja remover o Contrato('Sim' ou 'Nao'): "+opcao+"\n");
                                crudContrato.buscarContrato(opcao).imprimir();
                                if(linha.nextLine().toLowerCase().equals("sim")){
                                    crudContrato.deletar(opcao);
                                    System.err.println("Deletado com Sucesso!");
                                }else {
                                    System.err.println("Remover Cancelado!");

                                }
                            }
                            case 5 -> {
                                System.out.println("Como deseja buscar o contrato \n 1- Id do contrato \n2- Número do contrato: ");
                                int opcaoBusca = linha.nextInt();
                                linha.nextLine();

                                crudContrato.listar();
                                if(opcaoBusca == 1){
                                    System.out.println("Busca por Id do Contrato");
                                    System.out.println("Digite o Id do contrato: ");
                                    int idContrato = linha.nextInt();
                                    linha.nextLine();
                                    crudContrato.buscarContrato(idContrato).imprimir();
                                } else if (opcaoBusca == 2){
                                    System.out.println("Busca por Número de Contrato");
                                    System.out.println("Digite o número do contrato");
                                    String numeroBusca = linha.nextLine();
                                    crudContrato.buscarContrato(numeroBusca).imprimir();
                                }


                            }

                        }

                    }
                    case 9 -> {
                        execucao = false;
                    }
                    default -> {
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

    private static int menuInicial() throws OpcaoInvalidaException{
        System.out.println(AMARELO +"\nImobiliaria Shinigamis"+RESET);
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

    private static Cliente pegarInformacoesCliente() throws DadoInvalidoException{
        System.out.println(AZUL+"\nInforme o nome do cliente");
        String nome = linha.nextLine();
        System.out.println("\nInforme o CPF do cliente");
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


    private static int menuImoveis(Scanner linha) throws OpcaoInvalidaException{

        System.out.println(AMARELO +"Imobiliaria Shinigamis"+RESET);
        System.out.println("Escolha uma das opções do Menu Imoveis: ");
        System.out.println(ROXO+"1- Cadastrar imóvel");
        System.out.println("2- Editar informações do imóvel");
        System.out.println("3- Listar imóveis");
        System.out.println("4- Remover imóvel");
        System.out.println("5- Buscar imóvel");
        System.out.println(VERMELHO+"9- SAIR"+RESET);
        int opcao = linha.nextInt();
        linha.nextLine();
        return opcao;
    }

    private static Imovel pegarInformacoesImoveis() throws DadoInvalidoException {
        System.out.println(ROXO+"- Informe o endereço do novo imóvel -");
        Endereco endereco = new Endereco();
        System.out.println("Digite o CEP do novo endereço:");
        endereco.setCep(linha.nextLine());
        System.out.println("Digite o País do novo endereço:");
        endereco.setPais(linha.nextLine());
        System.out.println("Digite a Cidade do novo endereço:");
        endereco.setCidade(linha.nextLine());
        System.out.println("Digite o Estado do novo endereço:");
        endereco.setEstado(linha.nextLine());
        System.out.println("Digite a Rua do novo endereço:");
        endereco.setRua(linha.nextLine());
        System.out.println("Digite o Complemento do novo endereço:");
        endereco.setComplemento(linha.nextLine());
        System.out.println("Digite o Número do novo endereço:");
        endereco.setNumero(linha.nextLine());
        crudEndereco.criar(endereco);
        System.out.println("\n- Endereço do imóvel criado com sucesso! - ");
        System.out.println("Que tipo de imóvel você está informando?(Apartamento/Casa)");
        String tipo = linha.nextLine().toLowerCase();


        if (tipo.equals("apartamento") || tipo.equals("casa")) {
            if (tipo.equals("apartamento")) {
                return pegarInformacoesGeraisImoveis(TipoImovel.APARTAMENTO, endereco).apartamento;
            } else {
                return pegarInformacoesGeraisImoveis(TipoImovel.CASA, endereco).casa;
            }
        } else {
            System.out.println("Dado inválido!");
        }

        return null;
    }

    private static CasaEApartamento pegarInformacoesGeraisImoveis(TipoImovel tipo, Endereco endereco) throws DadoInvalidoException {

        CasaEApartamento imovel = new CasaEApartamento();

        System.out.println("Quantos quartos este imóvel contêm?");
        int quartoQntd = linha.nextInt();

        System.out.println("Quantos banheiros este imóvel contêm?");
        int banheiroQntd = linha.nextInt();

        System.out.println("Valor mensal deste imóvel:");
        double mensalValor = linha.nextDouble();

        System.out.println("Valor do condomínio deste imóvel:");
        double condominioValor = linha.nextDouble(); linha.nextLine();

        if (tipo == TipoImovel.CASA) {

            System.out.println("Essa casa possui area de lazer? (Sim/Não)");
            boolean lazer = linha.nextLine().equalsIgnoreCase("sim");


            System.out.println("Essa casa possui garagem? (Sim/Não)");
            boolean garagem = linha.nextLine().equalsIgnoreCase("sim");

            imovel.casa.setTipoImovel(TipoImovel.CASA);
            imovel.casa.setQntdQuartos(quartoQntd);
            imovel.casa.setQntdBanheiros(banheiroQntd);
            imovel.casa.setValorMensal(mensalValor);
            imovel.casa.setCondominio(condominioValor);
            imovel.casa.setAreaDeLazer(lazer);
            imovel.casa.setGaragem(garagem);
            imovel.casa.setEndereco(endereco);

        } else if (tipo == TipoImovel.APARTAMENTO) {

            System.out.println("Neste apartamento permite animais?");
            boolean animais = linha.nextLine().equalsIgnoreCase("sim");

            System.out.println("Neste apartamento há salão de festa?");
            boolean festa = linha.nextLine().equalsIgnoreCase("sim");

            System.out.println("Quantas vagas há neste apartamento?");
            int vagas = linha.nextInt();

            imovel.apartamento.setTipoImovel(TipoImovel.APARTAMENTO);
            imovel.apartamento.setQntdQuartos(quartoQntd);
            imovel.apartamento.setQntdBanheiros(banheiroQntd);
            imovel.apartamento.setValorMensal(mensalValor);
            imovel.apartamento.setCondominio(condominioValor);
            imovel.apartamento.setPermiteAnimais(animais);
            imovel.apartamento.setSalaoDeFesta(festa);
            imovel.apartamento.setNumeroDeVagas(vagas);
            imovel.apartamento.setEndereco(endereco);

        }

        return imovel;
    }


    // Contrato
    private static int menuContrato(Scanner linha) throws OpcaoInvalidaException{

        System.out.println(AMARELO +"Imobiliaria Shinigamis"+RESET);
        System.out.println("Escolha uma das opções do Menu contrato: ");
        System.out.println(VERDE+"1- Cadastrar Contrato");
        System.out.println("2- Editar informações do contrato");
        System.out.println("3- Listar contratos");
        System.out.println("4- Remover contrato");
        System.out.println("5- Buscar contrato");
        System.out.println(VERMELHO+"9- SAIR"+RESET);
        int opcao = linha.nextInt();
        linha.nextLine();
        return opcao;
    }

    private static Contrato pegarInformacoesContrato() throws DadoInvalidoException{
        System.out.println("Digite o numero do contrato: ");
        String numeroDeContrato = linha.nextLine();

        System.out.println("Escolha o Locador: ");
        crudCliente.listarClientesLocadorOuLocatario(TipoCliente.LOCADOR);
        int idLocador = linha.nextInt();
        linha.nextLine();

        System.out.println("Escolha o Locatario: ");
        crudCliente.listarClientesLocadorOuLocatario(TipoCliente.LOCATARIO);
        int idLocatario = linha.nextInt();
        linha.nextLine();

        System.out.println("Escolha o imovel: ");
        crudImovel.listaImoveisDisponiveis();
        int idImovel = linha.nextInt();
        linha.nextLine();

        System.out.println("Digite a data de entrada: ");
        String dataEntrada = linha.nextLine();

        System.out.println("Gigite a data de vencimento: ");
        String dataVencimento = linha.nextLine();

        Cliente locador = crudCliente.buscarClienteLocadorLocatario(idLocador, TipoCliente.LOCADOR);
        Cliente locatario = crudCliente.buscarClienteLocadorLocatario(idLocatario, TipoCliente.LOCATARIO);

        Imovel imovel = crudImovel.buscarImovelDisponivel(idImovel);
        imovel.setAlugado(true);
        double valorMensal = imovel.getValorMensal() + imovel.getCondominio();

        return new Contrato(numeroDeContrato, locador, locatario, valorMensal, dataEntrada, dataVencimento);
    }

}