

package model;

import java.util.Objects;
import main.SessaoLocal;
import persistencia.ClienteDAO;

public class Cliente extends Usuario{
    
    
    
    
    @Override
    public boolean isLogado() {
        return Objects.equals(SessaoLocal.getInstance().getUsuario().getId(), this.id);
    }

    @Override
    public boolean login(String username, String senha) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getByLogin(username);
        if (cliente.getSenha().equals(senha)){
            this.setId(cliente.id);
            this.setEndereco(cliente.endereco);
            this.setLogin(cliente.login);
            this.setNome(cliente.nome);
            this.setTelefone(cliente.telefone);
            this.setCpf(cliente.cpf);
            
            SessaoLocal.getInstance().setUsuario(this);
            return true;
        }
        else{
            return false;
        }
    }
    
    
    
}
