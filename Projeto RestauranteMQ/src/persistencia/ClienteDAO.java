

package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

public class ClienteDAO implements Dao<Cliente, Long>{

    @Override
    public void save(Cliente entity) {

        try {
            ConexaoPostgreSQL conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            
            String sql = "insert into cliente (cpf, nome, endereco, telefone, login, senha)  "+
                         "values(?,?,?,?,?,?)";
            
            
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
        } 
        
        
    }

    
    @Override
    public void delete(Long id) {
<<<<<<< HEAD
        
         try {
            ConexaoPostgreSQL conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            
            String sql = "delete from cliente where cliente.id = "+id;
            
            
            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                
                
                ps.execute();
                
                conn.fechar();
            }
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
=======
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> origin/master
    }

    @Override
    public List<Cliente> listAll() {
        
        List<Cliente> lista = new ArrayList<>();
        
         try {
            ConexaoPostgreSQL conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            
            String sql = "select * from cliente";
            
            
            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    Cliente c = new Cliente();
                    
                    c.setId(rs.getLong("id"));
                    c.setCpf(rs.getString("cpf"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone( rs.getString("telefone"));
                    c.setLogin( rs.getString("login"));
                    c.setSenha( rs.getString("senha"));
                    c.setNome( rs.getString("nome"));
                    lista.add(c);
                }      
                conn.fechar();
            }
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
        
    }
    
}
