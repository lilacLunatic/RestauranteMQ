/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Mesa;

/**
 *
 * @author Aluno
 */
public class MesaDAO implements Dao<Mesa, Long>{

    @Override
    public void save(Mesa entity) {
        
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "insert into mesa (numero, lugares)  "
                    + "values(?,?)";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setInt(1, entity.getNumero());
                ps.setInt(2, entity.getQuantidadeDeLugares());
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

            String sql = "delete from mesa where id = ?";

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
    public List<Mesa> listAll() {
        List<Mesa> lista = new ArrayList<>();

        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from mesa";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Mesa c = new Mesa();

                    c.setId(rs.getLong("id"));
                    c.setNumero(rs.getInt("numero"));
                    c.setQuantidadeDeLugares(rs.getInt("lugares"));
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
    public Mesa getById(Long pk) {
        Mesa c = new Mesa();
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from mesa where id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ps.setLong(1, pk);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    c.setId(rs.getLong("id"));
                    c.setNumero(rs.getInt("numero"));
                    c.setQuantidadeDeLugares(rs.getInt("lugares"));
                    
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
    
}
