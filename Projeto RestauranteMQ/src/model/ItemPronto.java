/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Aluno
 */
public class ItemPronto extends Item{
    
    private int quantidadeEstoque;

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    
    

    @Override
    public boolean isDisponivel() {
        return quantidadeEstoque > 0;
    }

    @Override
    public void deduzQuantidade() {
        super.deduzQuantidade();
        quantidadeEstoque--;
        //Salvar no banco (?)
    }
    
}
