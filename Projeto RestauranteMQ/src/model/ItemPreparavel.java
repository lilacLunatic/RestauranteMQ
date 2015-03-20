/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Map;

/**
 *
 * @author Aluno
 */
public class ItemPreparavel extends Item{
    
    private Map<Ingrediente, Integer> receita;

    public Map<Ingrediente, Integer> getReceita() {
        return receita;
    }

    public void setReceita(Map<Ingrediente, Integer> receita) {
        this.receita = receita;
    }
    
    

    @Override
    public boolean isDisponivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
