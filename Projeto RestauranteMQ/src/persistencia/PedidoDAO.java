/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Item;
import model.ItemPreparavel;
import model.Pedido;

/**
 *
 * @author Thiago
 */
public class PedidoDAO implements Dao<Pedido, Long>{

    
    
    @Override
    public void save(Pedido entity) {
        
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "insert into pedido (data, cliente, endereco, observacoes)  "
                    + "values(?,?,?,?)";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setDate(1, new java.sql.Date(entity.getDataEHora().getTimeInMillis()));
                ps.setLong(2, entity.getCliente().getId());
                ps.setString(3, entity.getEndereco());
                ps.setString(4, entity.getObservações());
               
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

            String sql = "delete from pedido where id = ?";

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
    public List<Pedido> listAll() {
         List<Pedido> lista = new ArrayList<>();

        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from pedido";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Pedido c = new Pedido();

                    c.setId(rs.getLong("id"));
                    Calendar data = Calendar.getInstance(); 
                    data.setTime(rs.getTimestamp("data"));
                    c.setDataEHora(data);
                    c.setEndereco(rs.getString("endereco"));
                    c.setObservações(rs.getString("observacoes"));
                    ClienteDAO clienteDao = new ClienteDAO();
                    c.setCliente(clienteDao.getById(rs.getLong("cliente")));

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
    public Pedido getById(Long pk) {
        Pedido c = new Pedido();
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from pedido where id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ps.setLong(1, pk);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){

                c = new Pedido();

                    c.setId(rs.getLong("id"));
                    Calendar data = Calendar.getInstance(); 
                    data.setTime(rs.getDate("data"));
                    c.setDataEHora(data);
                    c.setEndereco(rs.getString("endereco"));
                    c.setObservações(rs.getString("observacoes"));
                    ClienteDAO clienteDao = new ClienteDAO();
                    c.setCliente(clienteDao.getById(rs.getLong("cliente")));
                    
                } else {
                    throw new Exception("Não há pedido com o id " + pk);
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
    
    public void adicionaItem(int item, int pedido){
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "insert into pedidopossuiitem(pedido, item) values(?,?)";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pedido);
                ps.setInt(2, item);
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
    
    public Pedido getLastPedido(){
        ConexaoPostgreSQL conn = null;
        Pedido c = new Pedido();
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from pedido " +
                         "order by data desc limit 1";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if(rs.next()){              

                        c.setId(rs.getLong("id"));
                        Calendar data = Calendar.getInstance(); 
                        data.setTime(rs.getDate("data"));
                        c.setDataEHora(data);
                        c.setEndereco(rs.getString("endereco"));
                        c.setObservações(rs.getString("observacoes"));
                        ClienteDAO clienteDao = new ClienteDAO();
                        c.setCliente(clienteDao.getById(rs.getLong("cliente")));
                }

                conn.fechar();
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
    
    public List<Long> getItensByPedido(Pedido pedido){
        List<Long> lista = new ArrayList();
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select itemdemenu.* from itemdemenu join pedidopossuiitem on pedidopossuiitem.item = itemdemenu.id where pedidopossuiitem.pedido = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                
                ps.setLong(1, pedido.getId());
   
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {         
                    lista.add(rs.getLong("id"));
                }

                conn.fechar();
            }

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (conn != null) {
                conn.fechar();
            }
        }
        return lista;
    }
    
}
