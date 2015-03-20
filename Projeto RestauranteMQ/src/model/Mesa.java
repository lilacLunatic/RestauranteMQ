/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

public class Mesa {
    private Long id;
    private int numero ;
    private int quantidadeDeLugares;

    public int getNumero() {
        return numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getQuantidadeDeLugares() {
        return quantidadeDeLugares;
    }

    public void setQuantidadeDeLugares(int quantidadeDeLugares) {
        this.quantidadeDeLugares = quantidadeDeLugares;
    }
    
    
    
}
