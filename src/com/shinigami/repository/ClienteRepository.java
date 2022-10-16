package model.com.shinigami.repository;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.model.Cliente;
import model.com.shinigami.model.TipoCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements Repositorio<Integer, Cliente> {
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
    public Cliente adicionar(Cliente cliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            cliente.setIdCliente(proximoId);

            String sql = "INSERT INTO CLIENTE\n" +
                    "(id_cliente, nome , cpf , telefone , email , tipo_cliente)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, cliente.getIdCliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEmail());
            stmt.setInt(6,cliente.getTipoCliente().ordinal());

            int res = stmt.executeUpdate();
            System.out.println("adicionarCliente.res=" + res);
            return cliente;
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
    public boolean editar(Integer id, Cliente cliente) throws BancoDeDadosException {
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
    public List<Cliente> listar() throws BancoDeDadosException {
        List<Cliente> clientes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CLIENTE";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Cliente cliente = new Cliente();
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

    public Cliente buscarCliente(Integer id) throws BancoDeDadosException{
        Cliente cliente = new Cliente();
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

    public Cliente buscarCliente(String txt) throws BancoDeDadosException{
        Cliente cliente = new Cliente();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM CLIENTE WHERE UPPER(nome) LIKE ? or UPPER(telefone) LIKE ? or UPPER(cpf) LIKE ? or UPPER(email) LIKE ?";

            PreparedStatement stmt = con.prepareStatement(sql.toString());
            txt = txt.toUpperCase();
            stmt.setString(1, txt);
            stmt.setString(2, txt);
            stmt.setString(3, txt);
            stmt.setString(4, txt);


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
