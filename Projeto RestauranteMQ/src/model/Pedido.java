/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Calendar;
import java.util.List;

public class Pedido {
    private Long id;
    private Calendar dataEHora;
    private String observações;
    private String endereco;
    private Cliente cliente;
    private List<Item> itens;

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDataEHora() {
        return dataEHora;
    }

    public void setDataEHora(Calendar dataEHora) {
        this.dataEHora = dataEHora;
    }

    public String getObservações() {
        return observações;
    }

    public void setObservações(String observações) {
        this.observações = observações;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
}
