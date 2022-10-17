package model.com.shinigami.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.Contrato;

public class ContratoRepository implements Repositorio<Integer, Contrato> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_contrato.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Contrato adicionar(Contrato contrato) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();

            Integer idProxContrato = getProximoId(con);
            contrato.setIdContrato(idProxContrato);

            String sql = "INSERT INTO CONTRATO\n"+
                         " (ID_CONTRATO, VALOR_ALUGUEL, DATA_ENTRADA, DATA_VENCIMENTO, ID_LOCATARIO, ID_LOCADOR, ID_IMOVEL, ATIVO)" +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, contrato.getIdContrato());
            stmt.setDouble(2, contrato.getValorAluguel());
            stmt.setDate(3, Date.valueOf(contrato.getDataEntrada()));
            stmt.setDate(4, Date.valueOf(contrato.getDataVencimento()));
            stmt.setInt(5, contrato.getLocatario().getIdCliente());
            stmt.setInt(6, contrato.getLocador().getIdCliente());
            stmt.setInt(7, contrato.getImovel().getIdImovel());
            stmt.setString(8, contrato.isAtivo() ? "T" : "F");

            int res = stmt.executeUpdate();
            System.out.println("adicionarContrato.res=" + res);
            ImovelRepository imovelRepository = new ImovelRepository();
            contrato.getImovel().setAlugado(true);
            imovelRepository.editar(contrato.getImovel().getIdImovel(),contrato.getImovel());
            return contrato;

        }catch (BancoDeDadosException e) {
            e.getMessage();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (con != null){
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contrato;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();

            String sql = "UPDATE CONTRATO SET ativo = ? WHERE ID_CONTRATO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, "F");
            stmt.setInt(2, id);

            int res = stmt.executeUpdate();
            return res > 0;

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

    @Override
    public boolean editar(Integer id, Contrato contrato) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("UPDATE CONTRATO SET ");
            sql.append("DATA_ENTRADA = ?, ");
            sql.append("DATA_VENCIMENTO = ?, ");
            sql.append("ID_LOCATARIO = ?, ");
            sql.append("ID_LOCADOR = ? ");
            sql.append("WHERE ID_CONTRATO = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setDate(1, Date.valueOf(contrato.getDataEntrada()));
            stmt.setDate(2, Date.valueOf(contrato.getDataVencimento()));
            stmt.setInt(3, contrato.getLocatario().getIdCliente());
            stmt.setInt(4, contrato.getLocador().getIdCliente());
            stmt.setInt(5, id);

            int res = stmt.executeUpdate();
            System.out.println("editarContrato.res=" + res);

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
    public List<Contrato> listar() throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();

            List<Contrato> contratos = new ArrayList<>();

            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CONTRATO WHERE ativo LIKE 'T'";

            ResultSet res = stmt.executeQuery(sql);

            while(res.next()){
                Contrato contrato = new Contrato();

                ClienteRepository clienteRepository = new ClienteRepository();
                ImovelRepository imovelRepository = new ImovelRepository();

                contrato.setIdContrato(res.getInt("id_contrato"));
                contrato.setValorAluguel(res.getDouble("valor_aluguel"));
                contrato.setDataEntrada(res.getDate("data_entrada").toLocalDate());
                contrato.setDataVencimento(res.getDate("data_vencimento").toLocalDate());
                contrato.setLocatario(clienteRepository.buscarCliente(res.getInt("id_locatario")));
                contrato.setLocador(clienteRepository.buscarCliente(res.getInt("id_locador")));
                contrato.setImovel(imovelRepository.buscarImovel(res.getInt("id_imovel")));
                contrato.setAtivo(converteCharPraBoolean(res.getString("ativo")));
                contratos.add(contrato);
            }
            return contratos;

        } catch (SQLException e) {
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
    }

    public Contrato buscarContrato(int id) throws BancoDeDadosException{
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();

            ClienteRepository clienteRepository = new ClienteRepository();
            ImovelRepository imovelRepository = new ImovelRepository();

            String sql = "SELECT * FROM CONTRATO WHERE ID_CONTRATO = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();
            res.next();
            Contrato contrato = new Contrato();
            contrato.setIdContrato(res.getInt("id_contrato"));
            contrato.setValorAluguel(res.getDouble("valor_aluguel"));
            contrato.setDataEntrada(res.getDate("data_entrada").toLocalDate());
            contrato.setDataVencimento(res.getDate("data_vencimento").toLocalDate());
            contrato.setLocatario(clienteRepository.buscarCliente(res.getInt("id_locatario")));
            contrato.setLocador(clienteRepository.buscarCliente(res.getInt("id_locador")));
            contrato.setImovel(imovelRepository.buscarImovel(res.getInt("id_imovel")));
            contrato.setAtivo(converteCharPraBoolean(res.getString("ativo")));

            return contrato;

        } catch (SQLException e) {
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
    }
    private boolean converteCharPraBoolean(String valor){
        if(valor.toUpperCase().equals("T")){
            return true;
        }
        return false;
    }

}
