package model.com.shinigami.view;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.exceptions.OpcaoInvalidaException;
import model.com.shinigami.model.*;
import model.com.shinigami.service.ClienteService;
import model.com.shinigami.service.ContratoService;
import model.com.shinigami.service.EnderecoService;
import model.com.shinigami.service.ImovelService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
        ClienteService serviceCliente = new ClienteService();
        ContratoService contratoService = new ContratoService();
        ImovelService imovelService = new ImovelService();

        boolean execucao = true;
        while(execucao){
            int opcao;
            try{
                opcao = menuInicial();
                switch(opcao){
                    case 1 -> {//MENU CLIENTE
                        opcao = menuCliente();
                        switch (opcao){
                            case 1 ->{//cadastro
                                serviceCliente.adicionar(pegarInformacoesCliente());
                                System.out.println(VERDE+"Cliente cadastrado com Sucesso!"+RESET);
                            }
                            case 2 ->{//editar
                                System.out.println(AZUL+"\nQual usuario você deseja atualizar?"+RESET);
                                serviceCliente.listar();
                                String busca = linha.nextLine();
                                Cliente cliente = serviceCliente.buscarCliente(busca);
                                serviceCliente.editar(cliente.getIdCliente(), pegarInformacoesCliente());
                                System.out.println(VERDE+"Atualizado com Sucesso!"+RESET);
                            }
                            case 3 -> {//listar
                                serviceCliente.listar();
                            }
                            case 4 -> {//remover
                                System.out.println(AZUL+"\nQual usuario você deseja Remover? informe o ID"+RESET);
                                serviceCliente.listar();
                                String busca = linha.nextLine();
                                System.out.print(AZUL+"\nTem certeza que deseja remover o cliente: 'Sim' ou 'Nao' "+opcao+"\n"+RESET);
                                serviceCliente.buscarCliente(busca).imprimir();
                                if(linha.nextLine().toLowerCase().equals("sim")){
                                    serviceCliente.remover(serviceCliente.buscarCliente(busca).getIdCliente());
                                    System.out.println(VERDE+"Deletado com Sucesso!"+RESET);
                                }else {
                                    System.out.println(VERMELHO+"Remover Cancelado!"+RESET);
                                }

                            }case 5 -> {//buscar
                                System.out.println(AZUL+"Qual cliente você deseja buscar informações ? id/nome/telefone/cpf/email"+RESET);
                                String buscar = linha.nextLine();
                                Cliente cliente = serviceCliente.buscarCliente(buscar);
                                if(cliente == null){
                                    System.out.println(VERMELHO+"Cliente não encontrado!"+RESET);
                                }else{
                                    cliente.imprimir();
                                }

                            }default -> {
                                throw new OpcaoInvalidaException();
                            }
                        }
                    }
                    case 2 -> {//MENU IMOVEL
                        opcao = menuImovel();
                        switch (opcao) {

                            case 1 -> {//cadastro
                                Imovel imovel = pegarInformacoesImovel();

                                System.out.println(ROXO+"Qual Cliente é dono do Imovel?"+RESET);
                                serviceCliente.listar();
                                System.out.println(ROXO+"Informe o ID:"+RESET);
                                String idLocador = linha.nextLine();
                                imovel.setDono(serviceCliente.buscarCliente(idLocador));
                                imovelService.adicionar(imovel);
                                System.out.println(VERDE+"Imovel cadastrado com Sucesso!"+RESET);
                            }

                            case 2 -> {//editar
                                System.out.println(ROXO+"\nQual imóvel você deseja atualizar?"+RESET);
                                imovelService.listar();
                                Integer idImovel = linha.nextInt();
                                linha.nextLine();
                                imovelService.editar(idImovel, editaInformacoesImovel(imovelService, idImovel));
                                System.out.println(VERDE+"Atualizado com Sucesso!"+RESET);
                            }
                            case 3 -> {//listar
                                imovelService.listar();
                            }
                            case 4 -> {//remover
                                System.out.println(ROXO+"\nQual imóvel você deseja Remover? informe o ID"+RESET);
                                imovelService.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                System.out.print(VERMELHO+"\nTem certeza que deseja remover o imóvel? (Sim ou Não)\n"+RESET);
                                if(linha.nextLine().equalsIgnoreCase("sim")){
                                    imovelService.remover(opcao);
                                    System.out.println(VERDE+"Deletado com Sucesso!"+RESET);
                                }else {
                                    System.out.println(VERMELHO+"Remover Cancelado!"+RESET);

                                }
                            }
                            case 5 -> {//buscar
                                System.out.println(ROXO+"Informe o ID do IMOVEL"+RESET);
                                imovelService.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                imovelService.buscarImovel(opcao).imprimir();

                            }default -> {
                                throw new OpcaoInvalidaException();
                            }
                        }

                    }
                    case 3 -> {//MENU CONTRATOS
                        opcao = menuContrato();

                        switch (opcao){
                            case 1 -> {//cadastro
                                contratoService.adicionar(pegarInformacoesContrato(serviceCliente,imovelService));
                                System.out.println(VERDE+"Contrato criado com sucesso!"+RESET);
                            }
                            case 2 -> {//editar
                                System.out.println(VERDE+"\nQual contrato você deseja atualizar?"+RESET);
                                contratoService.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                contratoService.buscarContrato(opcao);
                                contratoService.editar(opcao, pegarInformacoesContrato(serviceCliente,imovelService));
                                System.out.println(VERDE+"Atualizado com Sucesso!"+RESET);
                            }
                            case 3 -> {//listar
                                contratoService.listar();
                            }
                            case 4 -> {//remover
                                System.out.println(VERDE+"\nQual contrato você deseja Remover? informe o ID"+RESET);
                                contratoService.listar();
                                opcao = linha.nextInt();
                                linha.nextLine();
                                contratoService.buscarContrato(opcao).imprimir();
                                System.out.print(VERDE+"\nTem certeza que deseja remover o Contrato('Sim' ou 'Nao'): "+opcao+"\n"+RESET);
                                if(linha.nextLine().toLowerCase().equals("sim")){
                                    contratoService.remover(opcao);
                                    System.out.println(VERDE+"Deletado com Sucesso!"+RESET);
                                }else {
                                    System.out.println(VERMELHO+"Remover Cancelado!"+RESET);
                                }
                            }
                            case 5 -> {//buscar
                                contratoService.listar();
                                System.out.println(VERDE+"Busca por Id do Contrato");
                                System.out.println("Digite o Id do contrato: "+RESET);
                                int idContrato = linha.nextInt();
                                linha.nextLine();
                                contratoService.buscarContrato(idContrato).imprimir();

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
            }catch (BancoDeDadosException e){
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
        Cliente cliente = new Cliente();
        System.out.println(AZUL+"\nInforme o nome do cliente");
        cliente.setNome(linha.nextLine());
        System.out.println("\nInforme o CPF do cliente");
        cliente.setCpf(linha.nextLine());
        System.out.println("\nInforme o email do cliente");
        cliente.setEmail(linha.nextLine());
        System.out.println("\nInforme o telefone do cliente");
        cliente.setTelefone(linha.nextLine());
        System.out.println("\nInforme se o cliente sera Locador(1) ou Locatario(2)"+RESET);
        int tipo = linha.nextInt();
        linha.nextLine();
        if(tipo == 1){
            cliente.setTipoCliente(TipoCliente.LOCADOR);
            return cliente;
        }
        cliente.setTipoCliente(TipoCliente.LOCATARIO);
        return cliente;
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
        endereco.setNumero(linha.nextInt());
        return endereco;
    }

    private static Imovel pegarInformacoesImovel() throws DadoInvalidoException {
        System.out.println(ROXO+"Que tipo de imóvel você está informando? Apartamento(1) ou Casa(2)"+RESET);
        int tipo = linha.nextInt();
        linha.nextLine();
        if(tipo != 1 && tipo != 2){
            throw new DadoInvalidoException();
        }
        TipoImovel tipoImovel = TipoImovel.APARTAMENTO;
        if(tipo == 2){
            tipoImovel = TipoImovel.CASA;
        }
        Endereco endereco = pegarInformacoesEndereco();

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
            linha.nextLine();
            if(vagas < 0){
                throw new DadoInvalidoException();
            }

            return new Apartamento(endereco,quartoQntd,banheiroQntd,mensalValor,condominioValor,tipoImovel,animais,festa,vagas);
        }else{
            throw new DadoInvalidoException();
        }
    }

    private static Imovel editaInformacoesImovel(ImovelService imovelService, Integer id) throws DadoInvalidoException, BancoDeDadosException{

        Imovel imovelBusca = imovelService.buscarImovel(id);

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

        imovelBusca.setValorMensal(mensalValor);
        imovelBusca.setCondominio(condominioValor);
        imovelBusca.setQntdBanheiros(banheiroQntd);
        imovelBusca.setQntdQuartos(quartoQntd);

        if( quartoQntd <= 0 || banheiroQntd <= 0 || mensalValor <= 0 || condominioValor < 0){
            throw new DadoInvalidoException();
        }
        if (imovelBusca.getTipoImovel().equals(TipoImovel.CASA)) {
            System.out.println(ROXO+"Essa casa possui area de lazer? (Sim/Não)");
            boolean lazer = linha.nextLine().equalsIgnoreCase("sim");

            System.out.println("Essa casa possui garagem? (Sim/Não)"+RESET);
            boolean garagem = linha.nextLine().equalsIgnoreCase("sim");

            ((Casa)imovelBusca).setGaragem(garagem);
            ((Casa)imovelBusca).setAreaDeLazer(lazer);
            return imovelBusca;

        } else if (imovelBusca.getTipoImovel().equals(TipoImovel.APARTAMENTO)) {
            System.out.println(ROXO+"Neste apartamento permite animais? (Sim/Não)");
            boolean animais = linha.nextLine().equalsIgnoreCase("sim");

            System.out.println("Neste apartamento há salão de festa? (Sim/Não)");
            boolean festa = linha.nextLine().equalsIgnoreCase("sim");

            ((Apartamento)imovelBusca).setPermiteAnimais(animais);
            ((Apartamento)imovelBusca).setSalaoDeFesta(festa);

            return imovelBusca;

        }else{
            throw new DadoInvalidoException();
        }
    }

    private static Contrato pegarInformacoesContrato(ClienteService clienteService,ImovelService imovelService) throws DadoInvalidoException,BancoDeDadosException{
        Contrato contrato = new Contrato();
        System.out.println("Escolha o Locatario: ");
        clienteService.listar();
        contrato.setLocatario(clienteService.buscarCliente(linha.nextLine()));

        System.out.println("Escolha o imovel: ");
        imovelService.listarImoveisDisponiveis();
        int idImovel = linha.nextInt();
        linha.nextLine();
        contrato.setImovel(imovelService.buscarImovel(idImovel));

        System.out.println("Digite a data de entrada: ");
        LocalDate dataEntrada = LocalDate.parse(linha.nextLine().trim(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
        contrato.setDataEntrada(dataEntrada);

        System.out.println("Digite a data de vencimento: "+RESET);
        LocalDate dataVencimento = (LocalDate.parse(linha.nextLine().trim(), DateTimeFormatter.ofPattern("d/MM/yyyy")));
        contrato.setDataVencimento(dataVencimento);

        Imovel imovel = imovelService.buscarImovel(idImovel);
        imovel.setAlugado(true);
        double valorMensal = imovel.getValorMensal() + imovel.getCondominio();
        contrato.setValorAluguel(valorMensal);
        contrato.setLocador(imovel.getDono());
        contrato.setAtivo(true);

        return contrato;
    }

}