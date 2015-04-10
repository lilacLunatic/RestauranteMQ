/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Funcionario;

public class FuncionarioDAO implements Dao<Funcionario, Long> {

    @Override
    public void save(Funcionario entity) {
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "insert into funcionario (salario, cpf, nome, endereco, telefone, data_de_entrada, login, senha, administrador)  "
                    + "values(?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setDouble(1, entity.getSalario());
                ps.setString(2, entity.getCpf());
                ps.setString(3, entity.getNome());
                ps.setString(4, entity.getEndereco());
                ps.setString(5, entity.getTelefone());
                ps.setDate(6, new java.sql.Date(entity.getDataDeEntrada().getTimeInMillis()));
                ps.setString(7, entity.getLogin());
                ps.setString(8, entity.getSenha());
                ps.setBoolean(9, entity.isAdministrador());
                ps.execute();

            }

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.fechar();
            }
        }
    }

    @Override
    public void delete(Long id) {

        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "delete from funcionario where funcionario.id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setLong(1, id);
                ps.execute();

                conn.fechar();
            }

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (conn != null) {
                conn.fechar();
            }
        }
    }

    @Override
    public List<Funcionario> listAll() {

        List<Funcionario> lista = new ArrayList<>();

        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from funcionario";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Funcionario c = new Funcionario();
                    Calendar demissao = Calendar.getInstance();
                    Date dateDemissao = rs.getDate("data_de_demissao");
                    if(dateDemissao != null)
                        demissao.setTime(dateDemissao);
                    else
                        demissao = null;
                    Calendar entrada = Calendar.getInstance();
                    Date dateEntrada = rs.getDate("data_de_entrada");
                    if(dateEntrada != null)
                        entrada.setTime(dateEntrada);
                    else
                        entrada = null;
                    c.setSalario(rs.getDouble("salario"));
                    c.setDataDeDemissao(demissao);
                    c.setDataDeEntrada(entrada);
                    c.setId(rs.getLong("id"));
                    c.setCpf(rs.getString("cpf"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setLogin(rs.getString("login"));
                    c.setSenha(rs.getString("senha"));
                    c.setNome(rs.getString("nome"));
                    c.setAdministrador(rs.getBoolean("administrador"));
                    lista.add(c);

                }

            }
            conn.fechar();

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (conn != null) {
                conn.fechar();
            }
        }

        return lista;

    }

    @Override
    public Funcionario getById(Long pk) {
        Funcionario c = new Funcionario();
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from funcionario where funcionario.id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ps.setLong(1, pk);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Calendar demissao = Calendar.getInstance();
                    Date dateDemissao = rs.getDate("data_de_demissao");
                    if(dateDemissao != null)
                        demissao.setTime(dateDemissao);
                    else
                        demissao = null;
                    Calendar entrada = Calendar.getInstance();
                    Date dateEntrada = rs.getDate("data_de_entrada");
                    if(dateEntrada != null)
                        entrada.setTime(dateEntrada);
                    else
                        entrada = null;
                    c.setSalario(rs.getDouble("salario"));
                    c.setDataDeDemissao(demissao);
                    c.setDataDeEntrada(entrada);
                    c.setId(rs.getLong("id"));
                    c.setCpf(rs.getString("cpf"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setLogin(rs.getString("login"));
                    c.setSenha(rs.getString("senha"));
                    c.setNome(rs.getString("nome"));
                    c.setAdministrador(rs.getBoolean("administrador"));
                } else {
                    throw new Exception("Não há funcionario com o id " + pk);
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.fechar();
            }
        }

        return c;

    }

    public Funcionario getByLogin(String login) {
        try {
            ConexaoPostgreSQL conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from funcionario where login = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setString(1, login);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Funcionario c = new Funcionario();
                    Calendar demissao = null;
                    Calendar entrada = null;

                    Date dateDemissao = rs.getDate("data_de_demissao");
                    if(dateDemissao != null){
                        demissao = Calendar.getInstance();
                        demissao.setTime(dateDemissao);
                    }
                    Date dateEntrada = rs.getDate("data_de_entrada");
                    if(dateEntrada != null){
                        entrada = Calendar.getInstance();
                        entrada.setTime(dateEntrada);
                    }

                    c.setSalario(rs.getDouble("salario"));
                    c.setDataDeDemissao(demissao);
                    c.setDataDeEntrada(entrada);
                    c.setId(rs.getLong("id"));
                    c.setCpf(rs.getString("cpf"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setLogin(rs.getString("login"));
                    c.setSenha(rs.getString("senha"));
                    c.setNome(rs.getString("nome"));
                    c.setAdministrador(rs.getBoolean("administrador"));

                    return c;

                } else {
                    throw new Exception("Não há cliente com o login " + login);
                }

            } finally {
                conn.fechar();
            }

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void updateDataDemissao(Funcionario funcionario) {
        try {
            ConexaoPostgreSQL conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "update funcionario set data_de_demissao = ? where id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                Date dateDemissao = new Date(funcionario.getDataDeDemissao().getTimeInMillis());
                ps.setDate(1, dateDemissao);
                ps.setLong(2, funcionario.getId());
                ps.execute();

            } finally {
                conn.fechar();
            }

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
