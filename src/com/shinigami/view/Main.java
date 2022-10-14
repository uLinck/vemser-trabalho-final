//package com.dbc.view;
//
//import com.dbc.model.Contato;
//import com.dbc.model.Pessoa;
//import com.dbc.model.TipoContato;
//import com.dbc.service.ContatoService;
//import com.dbc.service.PessoaService;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020
//
//        PessoaService pessoaService = new PessoaService();
//        ContatoService contatoService = new ContatoService();
//
//        int opcao = -1;
//        while (opcao != 0) {
//            System.out.println("Digite 1 para criar pessoa");
//            System.out.println("Digite 2 para listar pessoas");
//            System.out.println("Digite 3 para editar uma pessoa");
//            System.out.println("Digite 4 para excluir pessoas");
//            System.out.println("Digite 5 para criar contato");
//            System.out.println("Digite 6 para listar contatos");
//            System.out.println("Digite 7 para editar contatos");
//            System.out.println("Digite 8 para excluir contatos");
//            System.out.println("Digite 9 para listar contatos por código da pessoa");
//            System.out.println("Digite 0 para sair");
//            opcao = scanner.nextInt();
//            scanner.nextLine();
//            switch (opcao) {
//                case 1: {// adição
//                    Pessoa pessoa = new Pessoa();
//                    System.out.println("Digite o nome da pessoa");
//                    pessoa.setNome(scanner.nextLine());
//
//                    System.out.println("Digite a data de nascimento (dd/MM/yyyy)");
//                    String text = scanner.nextLine();
//                    pessoa.setDataNascimento(LocalDate.parse(text, formatter));
//
//                    System.out.println("Digite o cpf");
//                    pessoa.setCpf(scanner.nextLine());
//
//                    pessoaService.adicionarPessoa(pessoa);
//                    break;
//                }
//                case 2: {
//                    // listagem
//                    pessoaService.listarPessoas();
//                    break;
//                }
//                case 3: {
//                    // edição
//                    System.out.println("Qual pessoa você deseja editar?");
//                    pessoaService.listarPessoas();
//                    int index = scanner.nextInt();
//                    scanner.nextLine();
//
//                    Pessoa pessoaNova = new Pessoa();
//                    System.out.println("Digite o nome da pessoa");
//
//                    pessoaNova.setNome(scanner.nextLine());
//                    System.out.println("Digite a data de nascimento (dd/MM/yyyy)");
//
//                    String text = scanner.nextLine();
//
//                    pessoaNova.setDataNascimento(LocalDate.parse(text, formatter));
//                    System.out.println("Digite o cpf");
//
//                    pessoaNova.setCpf(scanner.nextLine());
//                    pessoaService.editarPessoa(index, pessoaNova);
//                    break;
//                }
//                case 4: {
//                    // exclusão
//                    System.out.println("Qual pessoa você deseja excluir?");
//                    pessoaService.listarPessoas();
//                    int id = scanner.nextInt();
//                    pessoaService.removerPessoa(id);
//                    break;
//                }
//
//                case 5: {
//                    // adição contato
//                    Contato contato = new Contato();
//                    System.out.println("Digite o codigo da pessoa para adicionar contato: ");
//                    pessoaService.listarPessoas();
//                    int index = scanner.nextInt();
//                    scanner.nextLine();
//
//                    Pessoa pessoaContato = new Pessoa();
//                    pessoaContato.setIdPessoa(index);
//                    contato.setPessoa(pessoaContato);
//
//                    System.out.println("Digite o tipo (1-RESIDENCIAL 2-COMERCIAL): ");
//                    contato.setTipoContato(TipoContato.ofTipo(scanner.nextInt()));
//                    scanner.nextLine();
//
//                    System.out.println("Digite o numero: ");
//                    contato.setNumero(scanner.nextLine());
//
//                    System.out.println("Digite a descricao: ");
//                    contato.setDescricao(scanner.nextLine());
//
//                    contatoService.adicionarContato(contato);
//                    break;
//                }
//                case 6: {
//                    // listagem
//                    contatoService.listar();
//                    break;
//                }
//                case 7: {
//                    // edição
//                    System.out.println("Qual contato você deseja editar?");
//                    contatoService.listar();
//                    int id = scanner.nextInt();
//                    scanner.nextLine();
//
//                    Contato contato = new Contato();
//                    System.out.println("Digite o codigo da pessoa para adicionar contato: ");
//                    pessoaService.listarPessoas();
//                    int index = scanner.nextInt();
//                    scanner.nextLine();
//
//                    Pessoa pessoaContato = new Pessoa();
//                    pessoaContato.setIdPessoa(index);
//                    contato.setPessoa(pessoaContato);
//
//                    System.out.println("Digite o tipo (1-RESIDENCIAL 2-COMERCIAL): ");
//                    int tipo = scanner.nextInt();
//                    TipoContato tipoContato = TipoContato.ofTipo(tipo);
//                    contato.setTipoContato(tipoContato);
//                    scanner.nextLine();
//
//                    System.out.println("Digite o numero: ");
//                    contato.setNumero(scanner.nextLine());
//
//                    System.out.println("Digite a descricao: ");
//                    contato.setDescricao(scanner.nextLine());
//
//                    contatoService.editar(id, contato);
//                    break;
//                }
//                case 8: {
//                    // exclusão
//                    System.out.println("Qual contato você deseja excluir?");
//                    contatoService.listar();
//                    boolean validouNumero = false;
//                    while (!validouNumero){
//                        try{
//                            int id = scanner.nextInt();
//                            contatoService.remover(id);
//                            validouNumero = true;
//                        } catch (InputMismatchException ex){
//                            System.err.println("numero invalido");
//                        }
//                    }
//                    break;
//                }
//                case 9: {
//                    System.out.println("Qual pessoa você deseja ver os contatos?");
//                    pessoaService.listarPessoas();
//                    int id = scanner.nextInt();
//                    scanner.nextLine();
//
//                    contatoService.listarContatoPorCodigoDaPessoa(id);
//                    break;
//                }
//                case 0:
//                    break;
//                default:
//                    System.err.println("opção inválida");
//                    break;
//            }
//        }
//    }
//}
