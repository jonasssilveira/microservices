package br.com.ecommerce.usuario.usecase.service;

import br.com.ecommerce.usuario.domain.entity.Usuario;
import br.com.ecommerce.usuario.domain.entity.dto.UserDTO;
import br.com.ecommerce.usuario.domain.entity.dto.Venda;
import br.com.ecommerce.usuario.usecase.service.interfaces.user.UserDAO;
import br.com.ecommerce.usuario.usecase.service.interfaces.venda.Vendas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class UserTransactions {

    private final Vendas vendas;
    private final UserDAO userDAO;

    public UserTransactions(final Vendas vendas, final UserDAO userDAO) {
        this.vendas = vendas;
        this.userDAO = userDAO;
     }

    private Optional<Venda> getSalesFromUser(Usuario user) {
        Optional<Venda> venda = vendas.getSalesFromUser(user);
        if(venda.isEmpty())
            return Optional.empty();
        return venda;
    }

    public Boolean deleteUser(String id) {
        UserDTO userDTO = getById(id);
        Usuario user = Usuario.builder().id(userDTO.getId()).build();
        if(getSalesFromUser(user).isEmpty())
            return this.userDAO.delete(user.getId());
        return false;
    }

    public Boolean createUser(Usuario user){
        Optional<Usuario> userByNome = this.userDAO.getByEmail(user.getEmail());
        if(userByNome.isEmpty()){
            this.userDAO.save(user);
            return true;
        }
        return false;
    }

    public Page<UserDTO> getAll(Pageable pageable){
        return this.userDAO.getAll(pageable);
    }

    public UserDTO getById(String id){
        return this.userDAO.getById(id);
    }

}
