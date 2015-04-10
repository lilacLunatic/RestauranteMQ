/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Objects;
import main.SessaoLocal;
import persistencia.FuncionarioDAO;

/**
 *
 * @author Aluno
 */
public class Funcionario extends Usuario {

    private double salario;
    private Calendar dataDeEntrada;
    private Calendar dataDeDemissao;
    private boolean administrador;

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

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

    
    @Override
    public boolean isLogado() {
        return Objects.equals(SessaoLocal.getInstance().getUsuario().getId(), this.id);
    }

    @Override
    public boolean login(String username, String senha) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.getByLogin(username);
        if (funcionario.getSenha().equals(senha)) {
            this.setId(funcionario.id);
            this.setEndereco(funcionario.endereco);
            this.setLogin(funcionario.login);
            this.setNome(funcionario.nome);
            this.setTelefone(funcionario.telefone);
            this.setCpf(funcionario.cpf);
            this.setDataDeEntrada(funcionario.dataDeEntrada);
            this.setDataDeDemissao(funcionario.dataDeDemissao);
            this.setSalario(funcionario.salario);
            SessaoLocal.getInstance().setUsuario(this);
            return true;
        } else {
            return false;
        }
    }

}
