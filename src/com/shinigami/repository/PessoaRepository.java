//package com.shinigami.repository;
//
//import com.dbc.exceptions.BancoDeDadosException;
//import com.dbc.model.Pessoa;
//import model.com.shinigami.exceptions.BancoDeDadosException;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PessoaRepository implements Repositorio<Integer, Pessoa> {
//    @Override
//    public Integer getProximoId(Connection connection) throws SQLException {
//        String sql = "SELECT seq_pessoa2.nextval mysequence from DUAL";
//
//        Statement stmt = connection.createStatement();
//        ResultSet res = stmt.executeQuery(sql);
//
//        if (res.next()) {
//            return res.getInt("mysequence");
//        }
//
//        return null;
//    }
//
//    @Override
//    public Pessoa adicionar(Pessoa pessoa) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            Integer proximoId = this.getProximoId(con);
//            pessoa.setIdPessoa(proximoId);
//
//            String sql = "INSERT INTO PESSOA\n" +
//                    "(ID_PESSOA, NOME, DATA_NASCIMENTO, CPF)\n" +
//                    "VALUES(?, ?, ?, ?)\n";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setInt(1, pessoa.getIdPessoa());
//            stmt.setString(2, pessoa.getNome());
//            stmt.setDate(3, Date.valueOf(pessoa.getDataNascimento()));
//            stmt.setString(4, pessoa.getCpf());
//
//            int res = stmt.executeUpdate();
//            System.out.println("adicionarPessoa.res=" + res);
//            return pessoa;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public boolean remover(Integer id) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "DELETE FROM PESSOA WHERE id_pessoa = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setInt(1, id);
//
//            // Executa-se a consulta
//            int res = stmt.executeUpdate();
//            System.out.println("removerPessoaPorId.res=" + res);
//
//            return res > 0;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public boolean editar(Integer id, Pessoa pessoa) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            StringBuilder sql = new StringBuilder();
//            sql.append("UPDATE PESSOA SET ");
//            sql.append(" cpf = ?,");
//            sql.append(" nome = ?,");
//            sql.append(" data_nascimento = ? ");
//            sql.append(" WHERE id_pessoa = ? ");
//
//            PreparedStatement stmt = con.prepareStatement(sql.toString());
//
//            stmt.setString(1, pessoa.getCpf());
//            stmt.setString(2, pessoa.getNome());
//            stmt.setDate(3, Date.valueOf(pessoa.getDataNascimento()));
//            stmt.setInt(4, id);
//
//            // Executa-se a consulta
//            int res = stmt.executeUpdate();
//            System.out.println("editarPessoa.res=" + res);
//
//            return res > 0;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public List<Pessoa> listar() throws BancoDeDadosException {
//        List<Pessoa> pessoas = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT * FROM PESSOA";
//
//            // Executa-se a consulta
//            ResultSet res = stmt.executeQuery(sql);
//
//            while (res.next()) {
//                Pessoa pessoa = new Pessoa();
//                pessoa.setIdPessoa(res.getInt("id_pessoa"));
//                pessoa.setNome(res.getString("nome"));
//                pessoa.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
//                pessoa.setCpf(res.getString("cpf"));
//                pessoas.add(pessoa);
//            }
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return pessoas;
//    }
//}
