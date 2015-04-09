

package model;

import java.util.Objects;
import main.SessaoLocal;
import persistencia.ClienteDAO;

public class Cliente extends Usuario{
    
    
    
    // TODO: implement inherited methods
    @Override
    public boolean isLogado() {
        return Objects.equals(SessaoLocal.getInstance().getUsuario().getId(), this.id);
    }

    @Override
    public boolean login(String username, String senha) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getByLogin(username);
        if (cliente.getSenha().equals(senha)){
            SessaoLocal.getInstance().setUsuario(this);
            return true;
        }
        else{
            return false;
        }
    }
    
    
    
}
