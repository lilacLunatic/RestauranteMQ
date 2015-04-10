/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

public enum Unidade {
    /**
     *
     */
    QUILOGRAMA, GRAMA, LITRO, MILILITRO, ADMENSIONAL, METRO, CENTIMETRO;
    
    static public boolean isMember(String name) {
       Unidade[] unidades = Unidade.values();
       for (Unidade unidade : unidades)
           if (unidade.toString().equals(name))
               return true;
       return false;
       
   }
}
