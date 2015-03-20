/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Calendar;
import java.util.List;

public class Reserva {
    private Calendar dataEHora;
    private List<Mesa> mesas;
    private Cliente cliente;
    private Long id;
    
    public Calendar getDataEHora() {
        return dataEHora;
    }

    public void setDataEHora(Calendar dataEHora) {
        this.dataEHora = dataEHora;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
}
