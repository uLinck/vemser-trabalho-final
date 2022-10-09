package model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Imobiliaria {
    private static final String VERMELHO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AZUL = "\u001B[34m";
    private static final String ROXO = "\u001B[35m";
    private static final String AMARELO = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    private static Scanner linha = new Scanner(System.in);


    public static void main(String[] args) {
        //Criando alguns dados
        ManipulaCliente crudCliente = new ManipulaCliente();
        ManipulaEndereco crudEndereco = new ManipulaEndereco();
        ManipulaImovel crudImovel = new ManipulaImovel();
        ManipulaContrato crudContrato = new ManipulaContrato();

        crudImovel.criar(new Apartamento(crudEndereco.enderecos.get(0),2,2,1600.00,300.00,TipoImovel.APARTAMENTO,true,true,1 ));
        crudImovel.buscarImovel(0).setDono(crudCliente.buscarCliente(0));

        crudImovel.criar(new Apartamento(crudEndereco.enderecos.get(1),6,3,4600.00,900.00,TipoImovel.APARTAMENTO,false,true,3 ));
        crudImovel.buscarImovel(1).setDono(crudCliente.buscarCliente(1));

        crudImovel.criar(new Casa(crudEndereco.enderecos.get(2),4,2,2600.00,0,TipoImovel.CASA,true,true));
        crudImovel.buscarImovel(2).setDono(crudCliente.buscarCliente(1));

        crudContrato.criar(new Contrato("001",crudImovel.buscarImovel(0),crudCliente.buscarCliente(2),1900,"10/01/2020","10/07/2020"));
        crudImovel.buscarImovel(0).setAlugado(true);
        boolean execucao = true;
        while(execucao){
            int opcao;
            try{
                opcao = Integer.parseInt(menuInicial());
                switch(opcao){
                    case 1 -> {//MENU CLIENTE
                        opcao = menuCliente();
                        switch (opcao){
                            case 1 ->{
                                crudCliente.criar(pegarInformacoesCliente());
                                System.out.println(VERDE+"Cliente cadastrado com Sucesso!"+RESET);
                            }
                            case 2 ->{
                                System.out.println(AZUL+"\nQual usuario você deseja atualizar?"+RESET);
                                crudCliente.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                crudCliente.buscarCliente(opcao);
                                crudCliente.atualizar(opcao, pegarInformacoesCliente());
                                System.out.println(VERDE+"Atualizado com Sucesso!"+RESET);
                            }
                            case 3 -> {
                                crudCliente.listar();
                            }
                            case 4 -> {
                                System.out.println(AZUL+"\nQual usuario você deseja Remover? informe o ID"+RESET);
                                crudCliente.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                System.out.print(AZUL+"\nTem certeza que deseja remover o cliente: 'Sim' ou 'Nao' "+opcao+"\n"+RESET);
                                crudCliente.buscarCliente(opcao).imprimir();
                                if(linha.nextLine().toLowerCase().equals("sim")){
                                    crudCliente.deletar(opcao);
                                    System.out.println(VERDE+"Deletado com Sucesso!"+RESET);
                                }else {
                                    System.out.println(VERMELHO+"Remover Cancelado!"+RESET);

                                }

                            }
                            case 5 -> {
                                System.out.println(AZUL+"Qual cliente você deseja buscar informações ? Nome ou ID"+RESET);
                                crudCliente.listar();
                                String buscar = linha.nextLine();
                                try{
                                    int id = Integer.parseInt(buscar);
                                    crudCliente.buscarCliente(id).imprimir();
                                    System.out.println("quantidade de contratos: "+crudContrato.buscarContrato(crudCliente.buscarCliente(id)).size()+RESET);
                                }catch (Exception e){
                                    crudCliente.buscarCliente(buscar).imprimir();
                                    System.out.println("quantidade de contratos: "+crudContrato.buscarContrato(crudCliente.buscarCliente(buscar)).size()+RESET);
                                }
                            }default -> {
                                throw new OpcaoInvalidaException();
                            }
                        }
                    }
                    case 2 -> {//MENU IMOVEL
                        opcao = menuImovel();
                        switch (opcao) {

                            case 1 -> {
                                Imovel imovel = pegarInformacoesImovel(crudEndereco);
                                System.out.println(ROXO+"Qual Cliente é dono do Imovel?"+RESET);
                                crudCliente.listar(TipoCliente.LOCADOR);
                                System.out.println(ROXO+"Informe o ID:"+RESET);
                                int idLocador = linha.nextInt();
                                linha.nextLine();
                                imovel.setDono(crudCliente.buscarCliente(idLocador,TipoCliente.LOCADOR));
                                crudImovel.criar(imovel);
                                System.out.println(VERDE+"Imovel cadastrado com Sucesso!"+RESET);
                            }

                            case 2 -> {
                                System.out.println(ROXO+"\nQual imóvel você deseja atualizar?"+RESET);
                                crudImovel.listar();
                                int idImovel = linha.nextInt();
                                linha.nextLine();
                                Imovel novo = pegarInformacoesImovel(crudEndereco);
                                novo.setDono(crudImovel.buscarImovel(idImovel).getDono());
                                crudImovel.atualizar(idImovel, novo);
                                System.out.println(VERDE+"Atualizado com Sucesso!"+RESET);
                            }
                            case 3 -> {
                                crudImovel.listar();
                            }
                            case 4 -> {
                                System.out.println(ROXO+"\nQual imóvel você deseja Remover? informe o ID"+RESET);
                                crudImovel.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                System.out.print(VERMELHO+"\nTem certeza que deseja remover o imóvel? (Sim ou Não)\n"+RESET);
                                if(linha.nextLine().equalsIgnoreCase("sim")){
                                    crudImovel.deletar(opcao);
                                    System.out.println(VERDE+"Deletado com Sucesso!"+RESET);
                                }else {
                                    System.out.println(VERMELHO+"Remover Cancelado!"+RESET);

                                }
                            }
                            case 5 -> {
                                System.out.println(ROXO+"Informe o ID do IMOVEL"+RESET);
                                crudImovel.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                crudImovel.buscarImovel(opcao).imprimir();

                            }default -> {
                                throw new OpcaoInvalidaException();
                            }
                        }

                    }
                    case 3 -> {//MENU CONTRATOS
                        opcao = menuContrato();

                        switch (opcao){
                            case 1 -> {
                                crudContrato.criar(pegarInformacoesContrato(crudCliente,crudImovel));
                                System.out.println(VERDE+"Contrato criado com sucesso!"+RESET);
                            }
                            case 2 -> {
                                System.out.println(VERDE+"\nQual contrato você deseja atualizar?"+RESET);
                                crudContrato.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                crudContrato.buscarContrato(opcao);
                                crudContrato.atualizar(opcao, pegarInformacoesContrato(crudCliente,crudImovel));
                                System.out.println(VERDE+"Atualizado com Sucesso!"+RESET);
                            }
                            case 3 -> {
                                crudContrato.listar();
                            }
                            case 4 -> {
                                System.out.println(VERDE+"\nQual contrato você deseja Remover? informe o ID"+RESET);
                                crudContrato.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                System.out.print(VERDE+"\nTem certeza que deseja remover o Contrato('Sim' ou 'Nao'): "+opcao+"\n"+RESET);
                                crudContrato.buscarContrato(opcao).imprimir();
                                if(linha.nextLine().toLowerCase().equals("sim")){
                                    crudContrato.deletar(opcao);
                                    System.out.println(VERDE+"Deletado com Sucesso!"+RESET);
                                }else {
                                    System.out.println(VERMELHO+"Remover Cancelado!"+RESET);

                                }
                            }
                            case 5 -> {
                                System.out.println(VERDE+"Como deseja buscar o contrato \n 1- Id do contrato \n2- Número do contrato: "+RESET);
                                int opcaoBusca = linha.nextInt();
                                linha.nextLine();

                                crudContrato.listar();
                                if(opcaoBusca == 1){
                                    System.out.println(VERDE+"Busca por Id do Contrato");
                                    System.out.println("Digite o Id do contrato: "+RESET);
                                    int idContrato = linha.nextInt();
                                    linha.nextLine();
                                    crudContrato.buscarContrato(idContrato).imprimir();
                                } else if (opcaoBusca == 2){
                                    System.out.println(VERDE+"Busca por Número de Contrato");
                                    System.out.println("Digite o número do contrato"+RESET);
                                    String numeroBusca = linha.nextLine();
                                    crudContrato.buscarContrato(numeroBusca).imprimir();
                                }


                            }default -> {
                                throw new OpcaoInvalidaException();
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
                System.out.println(VERMELHO+e.getMessage()+RESET);
            }catch (DadoInvalidoException e){
                System.out.println(VERMELHO+e.getMessage()+RESET);
            }catch (ListaVaziaException e){
                System.out.println(VERMELHO+e.getMessage()+RESET);
            }catch (IndexOutOfBoundsException e){
                System.out.println(VERMELHO+"Não encontrado! Tente Novamente\n"+RESET);
            }catch (InputMismatchException e){
                System.out.println(VERMELHO+"Dados Invalidos! Tente Novamente\n"+RESET);
            }catch (NumberFormatException e){
                System.out.println(VERMELHO+"Dados Invalidos! Tente Novamente\n"+RESET);
            }



        }
    }

    private static String menuInicial() throws OpcaoInvalidaException{
        System.out.println(AMARELO +"\nImobiliaria Shinigamis"+RESET);
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println(AZUL+"1- Menu Cliente"+RESET);
        System.out.println(ROXO+"2- Menu Imovel"+RESET);
        System.out.println(VERDE+"3- Menu Contratos"+RESET);
        System.out.println(VERMELHO+"9- VOLTAR"+RESET);
        String opcao = linha.nextLine();
        return opcao;

    }
    private static int menuCliente() throws OpcaoInvalidaException{

        System.out.println(AMARELO +"Imobiliaria Shinigamis"+RESET);
        System.out.println("Escolha uma das opções do Menu Cliente: ");
        System.out.println(AZUL+"1- Cadastrar Cliente");
        System.out.println("2- Editar Cliente");
        System.out.println("3- Listar Clientes");
        System.out.println("4- Remover Cliente");
        System.out.println("5- Buscar Cliente");
        System.out.println(VERMELHO+"9- VOLTAR"+RESET);
        int opcao = linha.nextInt();
        linha.nextLine();
        return opcao;
    }

    private static int menuImovel() throws OpcaoInvalidaException{

        System.out.println(AMARELO +"Imobiliaria Shinigamis"+RESET);
        System.out.println("Escolha uma das opções do Menu Imoveis: ");
        System.out.println(ROXO+"1- Cadastrar imóvel");
        System.out.println("2- Editar informações do imóvel");
        System.out.println("3- Listar imóveis");
        System.out.println("4- Remover imóvel");
        System.out.println("5- Buscar imóvel");
        System.out.println(VERMELHO+"9- VOLTAR"+RESET);
        int opcao = linha.nextInt();
        linha.nextLine();
        return opcao;
    }

    private static int menuContrato() throws OpcaoInvalidaException{

        System.out.println(AMARELO +"Imobiliaria Shinigamis"+RESET);
        System.out.println("Escolha uma das opções do Menu contrato: ");
        System.out.println(VERDE+"1- Cadastrar Contrato");
        System.out.println("2- Editar informações do contrato");
        System.out.println("3- Listar contratos");
        System.out.println("4- Remover contrato");
        System.out.println("5- Buscar contrato");
        System.out.println(VERMELHO+"9- VOLTAR"+RESET);
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

    private static Endereco pegarInformacoesEndereco() throws DadoInvalidoException {
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
        System.out.println("Digite o Número do novo endereço:"+RESET);
        endereco.setNumero(linha.nextLine());

        if(endereco.getCep().isBlank() || endereco.getPais().isBlank() || endereco.getCidade().isBlank() || endereco.getEstado().isBlank() ||
                endereco.getRua().isBlank() || endereco.getNumero().isBlank()){
            throw new DadoInvalidoException();
        }

        return  endereco;

    }

    private static Imovel pegarInformacoesImovel(ManipulaEndereco crudEndereco) throws DadoInvalidoException {
        System.out.println(ROXO+"Que tipo de imóvel você está informando? Apartamento(1) ou Casa(2)"+RESET);
        int tipo = linha.nextInt();
        linha.nextLine();
        if(tipo != 1 && tipo != 2){
            throw new DadoInvalidoException();
        }
        TipoImovel tipoImovel = TipoImovel.CASA;
        if(tipo == 2){
            tipoImovel = TipoImovel.APARTAMENTO;
        }
        Endereco endereco = pegarInformacoesEndereco();
        crudEndereco.criar(endereco);

        System.out.println(ROXO+"Quantos quartos este imóvel contêm?");
        int quartoQntd = linha.nextInt();
        linha.nextLine();

        System.out.println("Quantos banheiros este imóvel contêm?");
        int banheiroQntd = linha.nextInt();
        linha.nextLine();

        System.out.println("Valor mensal deste imóvel:");
        double mensalValor = linha.nextDouble();
        linha.nextLine();

        System.out.println("Valor do condomínio deste imóvel: (Informe '0' caso não tenha valor de condomínio)"+RESET);
        double condominioValor = linha.nextDouble();
        linha.nextLine();

        if( quartoQntd <= 0 || banheiroQntd <= 0 || mensalValor <= 0 || condominioValor < 0){
            throw new DadoInvalidoException();
        }
        if (tipoImovel.equals(TipoImovel.CASA)) {
            Imovel imovel = new Casa();
            System.out.println(ROXO+"Essa casa possui area de lazer? (Sim/Não)");
            boolean lazer = linha.nextLine().equalsIgnoreCase("sim");

            System.out.println("Essa casa possui garagem? (Sim/Não)"+RESET);
            boolean garagem = linha.nextLine().equalsIgnoreCase("sim");

            return new Casa(endereco,quartoQntd,banheiroQntd,mensalValor,condominioValor,tipoImovel,lazer,garagem);

        } else if (tipoImovel.equals(TipoImovel.APARTAMENTO)) {
            System.out.println(ROXO+"Neste apartamento permite animais? (Sim/Não)");
            boolean animais = linha.nextLine().equalsIgnoreCase("sim");

            System.out.println("Neste apartamento há salão de festa? (Sim/Não)");
            boolean festa = linha.nextLine().equalsIgnoreCase("sim");

            System.out.println("Quantas vagas há neste apartamento?"+RESET);
            int vagas = linha.nextInt();
            if(vagas < 0){
                throw new DadoInvalidoException();
            }

            return new Apartamento(endereco,quartoQntd,banheiroQntd,mensalValor,condominioValor,tipoImovel,animais,festa,vagas);
        }else{
            throw new DadoInvalidoException();
        }
    }

    private static Contrato pegarInformacoesContrato(ManipulaCliente crudCliente,ManipulaImovel crudImovel) throws DadoInvalidoException{

        System.out.println(VERDE+"Digite o numero do contrato: ");
        String numeroDeContrato = linha.nextLine();

        System.out.println("Escolha o Locatario: ");
        crudCliente.listar(TipoCliente.LOCATARIO);
        int idLocatario = linha.nextInt();
        linha.nextLine();

        System.out.println("Escolha o imovel: ");
        crudImovel.listaImovelDisponivel();
        int idImovel = linha.nextInt();
        linha.nextLine();

        System.out.println("Digite a data de entrada: ");
        String dataEntrada = linha.nextLine();

        System.out.println("Digite a data de vencimento: "+RESET);
        String dataVencimento = linha.nextLine();

        Cliente locatario = crudCliente.buscarCliente(idLocatario, TipoCliente.LOCATARIO);

        Imovel imovel = crudImovel.buscarImovelDisponivel(idImovel);
        imovel.setAlugado(true);
        double valorMensal = imovel.getValorMensal() + imovel.getCondominio();

        return new Contrato(numeroDeContrato, imovel, locatario, valorMensal, dataEntrada, dataVencimento);
    }

}