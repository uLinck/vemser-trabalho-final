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
                    "(id_cliente, nome , cpf , telefone , email , tipo_cliente,ativo)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, cliente.getIdCliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEmail());
            stmt.setInt(6, cliente.getTipoCliente().ordinal());
            stmt.setString(7, "T");

            int res = stmt.executeUpdate();
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

            String sql = "UPDATE CLIENTE SET ativo = ? WHERE id_cliente = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, "F");
            stmt.setInt(2, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();

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

            String sql = "UPDATE CLIENTE SET " +
                    " cpf = ?," +
                    " nome = ?," +
                    " telefone = ?," +
                    " email = ?," +
                    " tipo_cliente = ?" +
                    " WHERE id_cliente = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getTipoCliente().ordinal());
            stmt.setInt(6, id.intValue());

            // Executa-se a consulta
            int res = stmt.executeUpdate();

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

            String sql = "SELECT * FROM CLIENTE WHERE ATIVO LIKE 'T'";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(res.getInt("id_cliente"));
                cliente.setNome(res.getString("nome"));
                cliente.setTelefone(res.getString("telefone"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setEmail(res.getString("email"));
                cliente.setTipoCliente(res.getInt("tipo_cliente") == 0 ? TipoCliente.LOCADOR : TipoCliente.LOCATARIO);
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

    public Cliente buscarCliente(int id) throws BancoDeDadosException {
        Cliente cliente = new Cliente();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM CLIENTE WHERE id_cliente = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            System.out.println(stmt);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                cliente.setIdCliente(res.getInt("id_cliente"));
                cliente.setNome(res.getString("nome"));
                cliente.setTelefone(res.getString("telefone"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setEmail(res.getString("email"));
                cliente.setTipoCliente(res.getInt("tipo_cliente") == 0 ? TipoCliente.LOCADOR : TipoCliente.LOCATARIO);
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
        return cliente;
    }

    public Cliente buscarCliente(String txt) throws BancoDeDadosException {
        Cliente cliente = new Cliente();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM CLIENTE WHERE UPPER(nome) = ? or UPPER(telefone) = ? or UPPER(cpf) = ? or UPPER(email) = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            txt = txt.toUpperCase();
            stmt.setString(1, txt);
            stmt.setString(2, txt);
            stmt.setString(3, txt);
            stmt.setString(4, txt);


            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                cliente.setIdCliente(res.getInt("id_cliente"));
                cliente.setNome(res.getString("nome"));
                cliente.setTelefone(res.getString("telefone"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setEmail(res.getString("email"));
                cliente.setTipoCliente(res.getInt("tipo_cliente") == 0 ? TipoCliente.LOCADOR : TipoCliente.LOCATARIO);
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
        return cliente;
    }
}
