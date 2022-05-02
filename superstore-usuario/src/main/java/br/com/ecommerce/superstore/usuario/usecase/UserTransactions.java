package br.com.ecommerce.superstore.usuario.usecase;

import br.com.ecommerce.superstore.usuario.domain.entity.dto.EmailDTO;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.queue.Kafka;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.user.UserDAO;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.venda.Vendas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ecommerce.superstore.usuario.domain.entity.Usuario;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.UserDTO;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.Venda;

import java.util.Optional;

public class UserTransactions {

    private final Vendas vendas;
    private final UserDAO userDAO;
    private final Kafka kafka;
    public UserTransactions(final Vendas vendas, final UserDAO userDAO, Kafka kafka) {
        this.vendas = vendas;
        this.userDAO = userDAO;
        this.kafka = kafka;
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

    public void login(String email, String password) {
        Optional<Usuario> userbyEmailAndPassword = userDAO.findByEmailAndPassword(email, password);
        var user = userbyEmailAndPassword.get();
        if(user.getPrimeiroAcesso())
            kafka.send(new EmailDTO(user.getEmail(), "", ""));
    }

}
