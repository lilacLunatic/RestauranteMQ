

package main;

import model.Cliente;
import persistencia.ClienteDAO;

public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello, world!");
        
        Cliente c = new Cliente();
        c.setCpf("something");
        c.setTelefone("something");
        c.setNome("something");
        c.setSenha("something");
        c.setLogin("something");
        c.setEndereco("something");
        
        ClienteDAO cd = new ClienteDAO();
        cd.save(c);
        
    }
    
}