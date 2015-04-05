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
import model.Reserva;

/**
 *
 * @author Thiago
 */
public class ReservaDAO implements Dao<Reserva, Long>{

    @Override
    public void save(Reserva entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reserva> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva getById(Long pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Mesa> checkDisponibilidade(Calendar data, int numeroDeLugares) {
         List<Mesa> lista = new ArrayList<>();

        ConexaoPostgreSQL conn = null;
        try {
            
            conn = new ConexaoPostgreSQL("localhost", "postgres", "postgres", "postgres");

            String sql = "select mesa.* from mesa "+
                         "join reserva on reserva.reserva_mesa = mesa.id"
                    +    "where reserva.data != "+new java.sql.Date(data.getTimeInMillis()) +"  and mesa.lugares = "+numeroDeLugares;

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

    
}
