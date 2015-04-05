/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;

public class ItemDAO implements Dao<Item, Long>{

    @Override
    public void save(Item entity) {
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "insert into itemdemenu (nome, preco, categoria, quantidadeestoque)  "
                    + "values(?,?,?,?)";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setString(1, entity.getNome());
                ps.setDouble(2, entity.getPreco());
                ps.setString(3, entity.getCategoria());
                ps.setInt(4, entity.getQuantidadeEstoque());
                
               
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
    public List<Item> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getById(Long pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
