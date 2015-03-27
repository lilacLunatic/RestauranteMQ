/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Calendar;

/**
 *
 * @author Aluno
 */
public class Funcionário extends Usuário{
    private double salario;
    private Calendar dataDeEntrada;
    private Calendar dataDeDemissao;
    

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Calendar getDataDeEntrada() {
        return dataDeEntrada;
    }

    public void setDataDeEntrada(Calendar dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }

    public Calendar getDataDeDemissao() {
        return dataDeDemissao;
    }

    public void setDataDeDemissao(Calendar dataDeDemissao) {
        this.dataDeDemissao = dataDeDemissao;
    }

    
    //TODO; implement inherited methods
    
    @Override
    public boolean isLogado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean login(String username, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
