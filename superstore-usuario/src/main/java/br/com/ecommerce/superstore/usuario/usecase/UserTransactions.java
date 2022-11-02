package br.com.ecommerce.superstore.usuario.usecase;

import br.com.ecommerce.superstore.usuario.adapters.kafka.Kafka;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.EmailDTO;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.UserDTO;
import br.com.ecommerce.superstore.usuario.domain.entity.model.Usuario;
import br.com.ecommerce.superstore.usuario.infraestrutura.feign.client.response.dto.VendaResponseDTO;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.user.UserDAO;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.venda.VendasClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserTransactions {

    private final VendasClient vendasClient;
    private final UserDAO userDAO;
    private final Kafka kafka;
    public UserTransactions(final VendasClient vendasClient, final UserDAO userDAO, Kafka kafka) {
        this.vendasClient = vendasClient;
        this.userDAO = userDAO;
        this.kafka = kafka;
    }

    private List<VendaResponseDTO> getSalesFromUser(Usuario user) {
        List<VendaResponseDTO> venda = vendasClient.getAllPedidosByUser(user.getId());
        if(venda.isEmpty())
            return Collections.emptyList();
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
