

package persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

public class ClienteDAO implements Dao<Cliente, Long>{

    @Override
    public void save(Cliente entity) {
        

        
        try {
            ConexaoPostgreSQL conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            
            String sql = "insert into Cliente(cpf, nome, endereco, telefone, login, senha)  "+
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
