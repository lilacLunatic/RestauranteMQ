/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import persistencia.IngredienteDAO;

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
        for (Ingrediente ingrediente : receita.keySet()){
            if (ingrediente.getQuantidadeEstoque() < receita.get(ingrediente)){
                return false;
            }
        }
        
        return true;
    }

    @Override
    public void deduzQuantidade() {
        super.deduzQuantidade();
        for (Ingrediente ingrediente : receita.keySet()){
            final int quantidadeEstoque = ingrediente.getQuantidadeEstoque();
            final Integer quantidadeDeduzida = receita.get(ingrediente);
            ingrediente.setQuantidadeEstoque
                (quantidadeEstoque - quantidadeDeduzida);
        }
    }
    
}
