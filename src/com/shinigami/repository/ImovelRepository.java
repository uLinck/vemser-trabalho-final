package model.com.shinigami.repository;

import model.com.shinigami.exceptions.BancoDeDadosException;
import model.com.shinigami.exceptions.DadoInvalidoException;
import model.com.shinigami.model.*;
import model.com.shinigami.service.ClienteService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImovelRepository implements Repositorio<Integer, Imovel> {

    private EnderecoRepository enderecoRepository = new EnderecoRepository();

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_imovel.nextval mysequence from DUAL";

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

            imovel.setEndereco(enderecoRepository.adicionar(imovel.getEndereco()));
            imovel.setAtivo(true);

                Integer imovelProxId = this.getProximoId(con);
                imovel.setIdImovel(imovelProxId);

                String sql = "INSERT INTO IMOVEL\n" +
                        "(id_imovel, valor_mensal, condominio, alugado, qntd_quartos, qntd_banheiros, tipo_imovel," +
                        " id_endereco, id_cliente, permite_animais, salao_de_festas,area_de_lazer,garagem, ativo)\n" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement stmt = con.prepareStatement(sql);


                stmt.setInt(1, imovel.getIdImovel());
                stmt.setDouble(2, imovel.getValorMensal());
                stmt.setDouble(3, imovel.getCondominio());
                stmt.setString(4, imovel.isAlugado() ? "T" : "F");
                stmt.setInt(5, imovel.getQntdQuartos());
                stmt.setInt(6, imovel.getQntdBanheiros());
                stmt.setInt(7, imovel.getTipoImovel().ordinal());
                stmt.setInt(8, imovel.getEndereco().getIdEndereco());
                stmt.setInt(9, imovel.getDono().getIdCliente());
                if(imovel.getTipoImovel() == TipoImovel.APARTAMENTO) {
                    stmt.setString(10, ((Apartamento) imovel).isPermiteAnimais() ? "T" : "F");
                    stmt.setString(11, ((Apartamento) imovel).isSalaoDeFesta() ? "T" : "F");
                    stmt.setString(12,null);
                    stmt.setString(13,null);
                }else {
                    stmt.setString(10,null);
                    stmt.setString(11,null);
                    stmt.setString(12, ((Casa)imovel).isAreaDeLazer() ? "T" : "F");
                    stmt.setString(13, ((Casa)imovel).isGaragem() ? "T" : "F");
                }
                stmt.setString(14, imovel.isAtivo() ? "T" : "F");

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

            String sql = "UPDATE IMOVEL SET ativo = ? WHERE id_imovel = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, "F");
            stmt.setInt(2, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerImovelPorId.res=" + res);

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
    public boolean editar(Integer id, Imovel imovel) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            //"(id_imovel, valor_mensal, condominio, alugado, qnt_quartos,
            // qntd_banheiros, tipo_imovel, id_endereco, id_cliente, permite_animais, salao_de_festas)\n"

                StringBuilder sql = new StringBuilder();
                //"(id_imovel, valor_mensal, condominio, alugado, qnt_quartos,
                // qntd_banheiros, tipo_imovel, id_endereco, id_cliente, permite_animais, salao_de_festas)\n

                sql.append("UPDATE APARTAMENTO SET");
                sql.append(" valor_mensal = ?,");
                sql.append(" condominio = ?,");
                sql.append(" alugado = ?,");
                sql.append(" qnt_quartos = ?,");
                sql.append(" qnt_banheiros = ?,");
                sql.append(" tipo_imovel = ?,");
                sql.append(" permite_animais = ?,");
                sql.append(" area_de_lazer = ?,");
                sql.append(" garagem = ?,");
                sql.append(" salao_de_festas = ?,");
                sql.append(" WHERE id_imovel = ?");

                PreparedStatement stmt = con.prepareStatement(sql.toString());

                stmt.setDouble(1, imovel.getValorMensal());
                stmt.setDouble(2, imovel.getCondominio());
                stmt.setString(3, imovel.isAlugado() ? "T" : "F");
                stmt.setInt(4, imovel.getQntdQuartos());
                stmt.setInt(5, imovel.getQntdBanheiros());
                stmt.setInt(6, imovel.getTipoImovel().ordinal());
                if(imovel.getTipoImovel() == TipoImovel.APARTAMENTO) {
                    stmt.setString(7, ((Apartamento) imovel).isSalaoDeFesta() ? "T" : "F");
                    stmt.setString(8, ((Apartamento) imovel).isPermiteAnimais() ? "T" : "F");
                    stmt.setString(9,null);
                    stmt.setString(10,null);
                }else{
                    stmt.setString(7,null);
                    stmt.setString(8,null);
                    stmt.setString(9, ((Casa)imovel).isAreaDeLazer() ? "T" : "F");
                    stmt.setString(10, ((Casa)imovel).isGaragem() ? "T" : "F");
                }
                stmt.setInt(11, id);

                //Executa-se a consulta
                int res = stmt.executeUpdate();
                System.out.println("editarImovel.res=" + res);
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
        List<Imovel> imoveis = new ArrayList<>();
        ClienteRepository clienteRepository = new ClienteRepository();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM IMOVEL WHERE ATIVO LIKE 'T'";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                if( res.getInt("tipo_imovel") == 0){
                    Imovel imovel = new Apartamento();

                    imovel.setIdImovel(res.getInt("id_imovel"));
                    imovel.setValorMensal(res.getDouble("valor_mensal"));
                    imovel.setCondominio(res.getDouble("condominio"));
                    imovel.setAlugado(res.getBoolean("alugado"));
                    imovel.setQntdQuartos(res.getInt("qntd_quartos"));
                    imovel.setQntdBanheiros(res.getInt("qntd_banheiros"));
                    ((Apartamento)imovel).setPermiteAnimais(converteCharPraBoolean(res.getString("permite_animais")));
                    ((Apartamento)imovel).setSalaoDeFesta(converteCharPraBoolean(res.getString("salao_de_festas")));
                    ((Apartamento)imovel).setNumeroDeVagas(res.getInt("numero_de_vagas"));
                    imovel.setDono(clienteRepository.buscarCliente(res.getInt("id_cliente")));

                    imoveis.add(imovel);

                } else{
                    Imovel imovel = new Casa();

                    imovel.setIdImovel(res.getInt("id_imovel"));
                    imovel.setValorMensal(res.getDouble("valor_mensal"));
                    imovel.setCondominio(res.getDouble("condominio"));
                    imovel.setAlugado(res.getBoolean("alugado"));
                    imovel.setQntdQuartos(res.getInt("qntd_quartos"));
                    imovel.setQntdBanheiros(res.getInt("qntd_banheiros"));
                    ((Casa)imovel).setAreaDeLazer(res.getBoolean("area_de_lazer"));
                    ((Casa)imovel).setGaragem(res.getBoolean("garagem"));
                    imovel.setDono(clienteRepository.buscarCliente(res.getInt("id_cliente")));

                    imoveis.add(imovel);
                }
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
        return imoveis;
    }

    public Imovel buscarImovel(Integer id) throws BancoDeDadosException{
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            EnderecoRepository enderecoRepository = new EnderecoRepository();

            String sql = "SELECT * FROM IMOVEL WHERE id_imovel = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                if (res.getInt("tipo_imovel") == 1) {
                    Imovel imovel = new Apartamento();

                    imovel.setIdImovel(res.getInt("id_imovel"));
                    imovel.setValorMensal(res.getDouble("valor_mensal"));
                    imovel.setCondominio(res.getDouble("condominio"));
                    imovel.setAlugado(res.getBoolean("alugado"));
                    imovel.setQntdQuartos(res.getInt("qntd_quartos"));
                    imovel.setQntdBanheiros(res.getInt("qntd_banheiros"));
                    ((Apartamento) imovel).setPermiteAnimais(converteCharPraBoolean(res.getString("permite_animais")));
                    ((Apartamento) imovel).setSalaoDeFesta(converteCharPraBoolean(res.getString("salao_de_festas")));
                    ((Apartamento) imovel).setNumeroDeVagas(res.getInt("numero_de_vagas"));
                    imovel.setEndereco(enderecoRepository.buscarEndereco(res.getInt("id_endereco")));

                    return imovel;

                } else {
                    Imovel imovel = new Casa();

                    imovel.setIdImovel(res.getInt("id_imovel"));
                    imovel.setValorMensal(res.getDouble("valor_mensal"));
                    imovel.setCondominio(res.getDouble("condominio"));
                    imovel.setAlugado(res.getBoolean("alugado"));
                    imovel.setQntdQuartos(res.getInt("qntd_quartos"));
                    imovel.setQntdBanheiros(res.getInt("qntd_banheiros"));
                    ((Casa) imovel).setAreaDeLazer(converteCharPraBoolean(res.getString("area_de_lazer")));
                    ((Casa) imovel).setGaragem(converteCharPraBoolean(res.getString("garagem")));
                    imovel.setEndereco(enderecoRepository.buscarEndereco(res.getInt("id_endereco")));

                    return imovel;
                }
            }else {
                throw new BancoDeDadosException("Imovel Não Encontrado!!");
            }
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
    }

    private boolean converteCharPraBoolean(String valor){
        if(valor.toUpperCase().equals("T")){
            return true;
        }
        return false;
    }
}

