package model.com.shinigami.repository;

import model.com.shinigami.model.Endereco;
import model.com.shinigami.exceptions.BancoDeDadosException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepository implements Repositorio<Integer, Endereco> {

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_endereco.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Endereco adicionar(Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            endereco.setIdEndereco(proximoId);

            String sql = "INSERT INTO ENDERECO\n" +
                         "(ID_ENDERECO, RUA, CIDADE, ESTADO, PAIS, COMPLEMENTO, NUMERO, CEP)\n" +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, endereco.getIdEndereco());
            stmt.setString(2, endereco.getRua());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getEstado());
            stmt.setString(5, endereco.getPais());
            stmt.setString(6, endereco.getComplemento());
            stmt.setInt(7, endereco.getNumero());
            stmt.setString(8, endereco.getCep());

            int res = stmt.executeUpdate();
            return endereco;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null){
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM ENDERECO WHERE id_endereco = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerEnderecoPorId.res=" + res);
            return res > 0;

        } catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }finally {
            try {
                if (con != null){
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean editar(Integer id, Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("UPDATE ENDERECO SET ");
            sql.append(" RUA = ?,");
            sql.append(" CIDADE = ?,");
            sql.append(" ESTADO = ?, ");
            sql.append(" PAIS = ?, ");
            sql.append(" COMPLEMENTO = ? ");
            sql.append(" NUMERO = ? ");
            sql.append(" CEP = ? ");
            sql.append(" WHERE ID_ENDERECO = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getCidade());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getPais());
            stmt.setString(5, endereco.getComplemento());
            stmt.setInt(6, endereco.getNumero());
            stmt.setString(7, endereco.getCep());
            stmt.setInt(8, id);

            int res = stmt.executeUpdate();
            return res > 0;

        } catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Endereco> listar() throws BancoDeDadosException {
        List<Endereco> enderecos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM ENDERECO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Endereco endereco = new Endereco();

                endereco.setIdEndereco(res.getInt("ID_ENDERECO"));
                endereco.setRua(res.getString("RUA"));
                endereco.setCidade(res.getString("CIDADE"));
                endereco.setEstado(res.getString("ESTADO"));
                endereco.setPais(res.getString("PAIS"));
                endereco.setComplemento(res.getString("COMPLEMENTO"));
                endereco.setNumero(res.getInt("NUMERO"));
                endereco.setCep(res.getString("CEP"));
                enderecos.add(endereco);
            }

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return enderecos;
    }

    public Endereco buscarEndereco(Integer id) throws BancoDeDadosException{
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();
            Endereco endereco = new Endereco();

            String sql = "SELECT * FROM ENDERECO WHERE ID_ENDERECO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            res.next();
            endereco.setIdEndereco(res.getInt("ID_ENDERECO"));
            endereco.setRua(res.getString("RUA"));
            endereco.setCidade(res.getString("CIDADE"));
            endereco.setEstado(res.getString("ESTADO"));
            endereco.setPais(res.getString("PAIS"));
            endereco.setComplemento(res.getString("COMPLEMENTO"));
            endereco.setNumero(res.getInt("NUMERO"));
            endereco.setCep(res.getString("CEP"));

            return endereco;
        }catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
