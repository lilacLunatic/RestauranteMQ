package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
//TODO: fazer update
public class ClienteDAO implements Dao<Cliente, Long> {

    @Override
    public void save(Cliente entity) {

        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "insert into cliente (cpf, nome, endereco, telefone, login, senha)  "
                    + "values(?,?,?,?,?,?)";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setString(1, entity.getCpf());
                ps.setString(2, entity.getNome());
                ps.setString(3, entity.getEndereco());
                ps.setString(4, entity.getTelefone());
                ps.setString(5, entity.getLogin());
                ps.setString(6, entity.getSenha());

                ps.execute();

                conn.fechar();
            }

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
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

            String sql = "delete from cliente where cliente.id = ?";

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
    public List<Cliente> listAll() {

        List<Cliente> lista = new ArrayList<>();

        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from cliente";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Cliente c = new Cliente();

                    c.setId(rs.getLong("id"));
                    c.setCpf(rs.getString("cpf"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setLogin(rs.getString("login"));
                    c.setSenha(rs.getString("senha"));
                    c.setNome(rs.getString("nome"));
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

    public Cliente getByLogin(String login) {
        try {
            ConexaoPostgreSQL conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from cliente where login = (?)";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setString(1, login);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Cliente c = new Cliente();

                    c.setId(rs.getLong("id"));
                    c.setCpf(rs.getString("cpf"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setLogin(rs.getString("login"));
                    c.setSenha(rs.getString("senha"));
                    c.setNome(rs.getString("nome"));

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

    @Override
    public Cliente getById(Long pk) {
        Cliente c = new Cliente();
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from cliente where cliente.id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ps.setLong(1, pk);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){

                c = new Cliente();

                    c.setId(rs.getLong("id"));
                    c.setCpf(rs.getString("cpf"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setLogin(rs.getString("login"));
                    c.setSenha(rs.getString("senha"));
                    c.setNome(rs.getString("nome"));
                    
                } else {
                    throw new Exception("Não há cliente com o id " + pk);
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
    
    public void updateNome(Cliente cliente){
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "update cliente set nome = ? where id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ps.setString(1, cliente.getNome());
                ps.setLong(2, cliente.getId());
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
    public void updateEndereco(Cliente cliente){
        
    }
    public void updateTelefone(Cliente cliente){
        
    }
    public void updateCpf(Cliente cliente){
        
    }
    public void updateSenha(Cliente cliente){
        
    }
}
