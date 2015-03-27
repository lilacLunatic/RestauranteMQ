

package main;

import java.util.List;
import model.Cliente;
import persistencia.ClienteDAO;

public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello, world!");
        
        Cliente c = new Cliente();
        
        
        ClienteDAO cd = new ClienteDAO();
        
        c = cd.getById(new Long(4));
        
        System.out.println("Nome = "+c.getNome() + "  ID = "+c.getId());
        
    }
    
}