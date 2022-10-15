package model.com.shinigami.repository;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImovelRepository implements Repositorio<Integer, Imovel> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_cliente.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Imovel adicionar(Imovel imovel) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            imovel.setIdImovel(proximoId);

            String sql = "INSERT INTO IMOVEL\n" +
                    "(id_imovel, valor_mensal, condominio, alugado, tipo_imovel, id_endereco, id_cliente  )\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, imovel.getIdImovel());
            stmt.setInt(2, imovel.getQntdQuartos());



            int res = stmt.executeUpdate();
            System.out.println("adicionarCliente.res=" + res);
            return imovel;
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
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM CLIENTE WHERE id_cliente = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerClientePorId.res=" + res);

            return res > 0;
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
    }

    @Override
    public boolean editar(Integer id, Imovel cliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            //("id_cliente", "nome" , "cpf" , "telefone" , "email" , "tipo_cliente")
            sql.append("UPDATE CLIENTE SET ");
            sql.append(" cpf = ?,");
            sql.append(" nome = ?,");
            sql.append(" telefone = ?, ");
            sql.append(" email = ?, ");
            sql.append(" tipo_cliente = ? ");
            sql.append(" WHERE id_pessoa = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getTipoCliente().ordinal());
            stmt.setInt(6, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarCliente.res=" + res);

            return res > 0;
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
    }

    @Override
    public List<Imovel> listar() throws BancoDeDadosException {
        List<Imovel> clientes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CLIENTE";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Imovel cliente = new Imovel();
                cliente.setIdCliente(res.getInt("id_cliente"));
                cliente.setNome(res.getString("nome"));
                cliente.setTelefone(res.getString("telefone"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setEmail(res.getString("email"));
                cliente.setTipoCliente(TipoCliente.values()[res.getInt("tipo_cliente")]);
                clientes.add(cliente);
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
        return clientes;
    }

    public Imovel buscarCliente(Integer id) throws BancoDeDadosException{
        Imovel cliente = new Imovel();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM CLIENTE WHERE id_cliente = ?";

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery(sql);

            cliente.setIdCliente(res.getInt("id_cliente"));
            cliente.setNome(res.getString("nome"));
            cliente.setTelefone(res.getString("telefone"));
            cliente.setCpf(res.getString("cpf"));
            cliente.setEmail(res.getString("email"));
            cliente.setTipoCliente(TipoCliente.values()[res.getInt("tipo_cliente")]);

        }catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }

    public Imovel buscarCliente(String txt) throws BancoDeDadosException{
        Imovel cliente = new Imovel();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM CLIENTE WHERE nome = ? or telefone = ? or cpf = ? or email = ?";

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery(sql);

            cliente.setIdCliente(res.getInt("id_cliente"));
            cliente.setNome(res.getString("nome"));
            cliente.setTelefone(res.getString("telefone"));
            cliente.setCpf(res.getString("cpf"));
            cliente.setEmail(res.getString("email"));
            cliente.setTipoCliente(TipoCliente.values()[res.getInt("tipo_cliente")]);

        }catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }
}
