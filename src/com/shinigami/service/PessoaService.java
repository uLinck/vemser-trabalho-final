//package com.dbc.service;
//
//import com.dbc.exceptions.BancoDeDadosException;
//import com.dbc.model.Pessoa;
//import com.dbc.repository.PessoaRepository;
//
//import java.util.List;
//
//public class PessoaService {
//    private PessoaRepository pessoaRepository;
//
//    public PessoaService() {
//        pessoaRepository = new PessoaRepository();
//    }
//
//    // criação de um objeto
//    public void adicionarPessoa(Pessoa pessoa) {
//        try {
//
//            if (pessoa.getCpf().length() != 11) {
//                throw new Exception("CPF Invalido!");
//            }
//
//            Pessoa pessoaAdicionada = pessoaRepository.adicionar(pessoa);
//            System.out.println("pessoa adicinada com sucesso! " + pessoaAdicionada);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.out.println("ERRO: " + e.getMessage());
////            System.out.println("TRACE: ");
////            e.printStackTrace();
//        }
//    }
//
//    // remoção
//    public void removerPessoa(Integer id) {
//        try {
//            boolean conseguiuRemover = pessoaRepository.remover(id);
//            System.out.println("pessoa removida? " + conseguiuRemover + "| com id=" + id);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // atualização de um objeto
//    public void editarPessoa(Integer id, Pessoa pessoa) {
//        try {
//            boolean conseguiuEditar = pessoaRepository.editar(id, pessoa);
//            System.out.println("pessoa editada? " + conseguiuEditar + "| com id=" + id);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // leitura
//    public void listarPessoas() {
//        try {
//            List<Pessoa> listar = pessoaRepository.listar();
//            listar.forEach(System.out::println);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//}
