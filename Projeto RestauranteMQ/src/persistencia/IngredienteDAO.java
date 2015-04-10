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
import model.Ingrediente;
import model.Unidade;

/**
 *
 * @author Aluno
 */
public class IngredienteDAO implements Dao<Ingrediente, Long> {

    @Override
    public void save(Ingrediente entity) {
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            
            String sql = "insert into Ingrediente (quantidadeestoque, nome, unidade) values (0, ?, ?)";
            
            try(PreparedStatement ps = conn.getConnection().prepareStatement(sql)){
                ps.setString(1, entity.getNome());
                ps.setString(2, entity.getUnidade().toString());
                
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

    @Override
    public void delete(Long id) {
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            
            String sql = "delete from ingrediente where id = ?";
            
            try(PreparedStatement ps = conn.getConnection().prepareStatement(sql)){
                ps.setLong(1, id);
                
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

    @Override
    public List<Ingrediente> listAll() {
        List<Ingrediente> result = new ArrayList<>();
        
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            
            String sql = "select * from ingrediente";
            
            try(PreparedStatement ps = conn.getConnection().prepareStatement(sql)){;
                
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()){
                    Ingrediente i = new Ingrediente();
                    i.setId(rs.getLong("ID"));
                    i.setNome(rs.getString("nome"));
                    i.setQuantidadeEstoque(rs.getInt("quantidadeestoque"));
                    i.setUnidade(Unidade.valueOf(rs.getString("unidade")));
                    result.add(i);
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(IngredienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.fechar();
            }
        }
        
        return result;
    }

    @Override
    public Ingrediente getById(Long pk) {
        Ingrediente ingrediente = new Ingrediente();
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from ingrediente where id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ps.setLong(1, pk);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    ingrediente.setId(rs.getLong("ID"));
                    ingrediente.setNome(rs.getString("nome"));
                    ingrediente.setQuantidadeEstoque(rs.getInt("quantiadeEstoque"));
                    ingrediente.setUnidade(Unidade.valueOf(rs.getString("unidade")));
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

        return ingrediente;
    }
    
    public void reestoque(Ingrediente entity, int quantidade){
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            
            String sql = "update ingrediente set quantidadeestoque = ? where id = ?";
            
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
    

}
