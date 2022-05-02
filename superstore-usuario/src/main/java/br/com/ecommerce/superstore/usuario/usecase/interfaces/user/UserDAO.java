package br.com.ecommerce.superstore.usuario.usecase.interfaces.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ecommerce.superstore.usuario.domain.entity.Usuario;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.UserDTO;
import java.util.Optional;

public interface UserDAO {
    Optional<Usuario> getByEmail(String nome);
    UserDTO getById(String id);
    Page<UserDTO> getAll(Pageable pageable);
    Usuario save(Usuario user);
    Boolean delete(String id);
    Optional<Usuario> findByEmailAndPassword(String email, String password);
}
