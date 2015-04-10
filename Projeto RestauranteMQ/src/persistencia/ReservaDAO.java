/*
 * To change this template, choose Tools | Templates
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
import model.Mesa;
import model.Pedido;
import model.Reserva;

/**
 *
 * @author Thiago
 */
public class ReservaDAO implements Dao<Reserva, Long>{

    @Override
    public void save(Reserva entity) {
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "insert into reserva (data, reserva_cliente, reserva_mesa)  "
                    + "values(TIMESTAMP '"+ new java.sql.Date(entity.getDataEHora().getTimeInMillis()) + " "+entity.getDataEHora().getTime().getHours() +":"+entity.getDataEHora().getTime().getMinutes() +"',?,?)";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                ps.setDate(1, new java.sql.Date(entity.getDataEHora().getTimeInMillis()));
                ps.setLong(1, entity.getCliente().getId());
                ps.setLong(2, entity.getMesa().getId());
                

                ps.execute();

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

            String sql = "delete from reserva where id = ?";

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
    public List<Reserva> listAll() {
        List<Reserva> lista = new ArrayList<>();

        ConexaoPostgreSQL conn = null;
        try {
            
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from reserva";
            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Reserva c = new Reserva();

                    c.setId(rs.getLong("id"));
                    Calendar data = Calendar.getInstance(); 
                    data.setTime(rs.getDate("data"));
                    c.setDataEHora(data);
                    ClienteDAO clienteDao = new ClienteDAO();
                    c.setCliente(clienteDao.getById(rs.getLong("reserva_cliente")));
                    MesaDAO mesaDao = new MesaDAO();
                    c.setMesa(mesaDao.getById(rs.getLong("reserva_mesa")));
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
    public Reserva getById(Long pk) {
        Reserva c = new Reserva();
        ConexaoPostgreSQL conn = null;
        try {
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select * from reserva where id = ?";

            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {

                ps.setLong(1, pk);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){

                c = new Reserva();

                    c.setId(rs.getLong("id"));
                    Calendar data = Calendar.getInstance(); 
                    data.setTime(rs.getDate("data"));
                    c.setDataEHora(data);
                    ClienteDAO clienteDao = new ClienteDAO();
                    c.setCliente(clienteDao.getById(rs.getLong("reserva_cliente")));
                    MesaDAO mesaDao = new MesaDAO();
                    c.setMesa(mesaDao.getById(rs.getLong("reserva_mesa")));
                    
                } else {
                    throw new Exception("Não há reserva com o id " + pk);
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
    
    public List<Mesa> checkDisponibilidade(Calendar data, int numeroDeLugares) {
         List<Mesa> lista = new ArrayList<>();

        ConexaoPostgreSQL conn = null;
        try {
            
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");
            

            String sql = "select mesa.*, reserva.data from mesa "+
                         "left join reserva on reserva.reserva_mesa = mesa.id"
                    +    " where reserva.data is null or reserva.data <>  TIMESTAMP '"+ new java.sql.Date(data.getTimeInMillis()) + " "+data.getTime().getHours() +":"+data.getTime().getMinutes() +"'  and mesa.lugares = ?";
            
            
            
            try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
                
                ps.setInt(1, numeroDeLugares);
                

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

    
}
