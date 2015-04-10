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
import model.ItemPronto;

public class ItemProntoDAO implements Dao<ItemPronto, Long>{

    @Override
    public void save(ItemPronto entity) {
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
    public List<ItemPronto> listAll() {
        List<ItemPronto> lista = new ArrayList<>();

        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from itemdemenu where quantidadeestoque is not null";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    ItemPronto c = new ItemPronto();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setPreco(rs.getDouble("preco"));
                    c.setCategoria(rs.getString("categoria"));
                    c.setQuantidadeEstoque(rs.getInt("quantidadeestoque"));
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
    public ItemPronto getById(Long pk) {
        ItemPronto c = new ItemPronto();
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
                    c.setQuantidadeEstoque(rs.getInt("quantidadeestoque"));
                    
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
    
    public void updateQuantidade(ItemPronto entity){
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "update itemdemenu set quantidadeestoque = ? where id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setLong(1, entity.getQuantidadeEstoque());
                ps.setLong(2, entity.getId());
                ps.executeUpdate();

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
    
    public void reestoque(ItemPronto entity, int quantidade){
        if(entity.getQuantidadeEstoque() + quantidade < 0){
            throw new IllegalArgumentException("Reestoque negativo acima do estoque");
        }
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            
            String sql = "update itemdemenu set quantidadeestoque = ? where id = ?";
            
            try(PreparedStatement ps = conn.getConnection().prepareStatement(sql)){
                ps.setInt(1, entity.getQuantidadeEstoque() + quantidade);
                ps.setLong(2, entity.getId());
                
                ps.execute();
            }
        } catch (Exception ex) {
            Logger.getLogger(IngredienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.fechar();
            }
        }
    }
    
    public List<String> getValorMedioPorCategoria(){
        List<String> retorno = new ArrayList<>();
        String sql = "select categoria, avg(preco) from itemdemenu group by categoria";
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            
            try(PreparedStatement ps = conn.getConnection().prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    retorno.add("Categoria: " + rs.getString("categoria") + "\n"
                            + "Media: " + rs.getDouble(2));
                }
                
                return retorno;
            }
        } catch (Exception ex) {
            Logger.getLogger(IngredienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return retorno;
        } finally {
            if (conn != null) {
                conn.fechar();
            }
        }
    }
    
}
