/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import model.Usuario;

/**
 *
 * @author Rael
 */
public class SessaoLocal {
    
    private Usuario usuario = null;
    
    private SessaoLocal() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public static SessaoLocal getInstance() {
        return SessaoLocalHolder.INSTANCE;
    }
    
    private static class SessaoLocalHolder {

        private static final SessaoLocal INSTANCE = new SessaoLocal();
    }
}
