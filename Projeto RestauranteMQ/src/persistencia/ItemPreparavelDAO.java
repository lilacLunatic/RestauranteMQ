/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ItemPreparavel;
import model.ItemPronto;

/**
 *
 * @author Thiago
 */
public class ItemPreparavelDAO implements Dao<ItemPreparavel, Long>{

    @Override
    public void save(ItemPreparavel entity) {
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "insert into itemdemenu (nome, preco, categoria, quantidadeestoque)  "
                    + "values(?,?,?)";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setString(1, entity.getNome());
                ps.setDouble(2, entity.getPreco());
                ps.setString(3, entity.getCategoria());
                
                
               
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

            String sql = "delete from itemdemenu where id = ?";

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
    public List<ItemPreparavel> listAll() {
        List<ItemPreparavel> lista = new ArrayList<>();

        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from itemdemenu";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    ItemPreparavel c = new ItemPreparavel();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setPreco(rs.getDouble("preco"));
                    c.setCategoria(rs.getString("categoria"));           
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
    public ItemPreparavel getById(Long pk) {
        ItemPreparavel c = new ItemPreparavel();
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from itemdemenu where id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ps.setLong(1, pk);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setPreco(rs.getDouble("preco"));
                    c.setCategoria(rs.getString("categoria"));
                    
                    
                } else {
                    throw new Exception("Não há item com o id " + pk);
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
