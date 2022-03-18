package br.com.ecommerce.usuario.usecase.service.interfaces.user;

import br.com.ecommerce.usuario.domain.entity.Usuario;
import br.com.ecommerce.usuario.domain.entity.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserDAO {
    Optional<Usuario> getByEmail(String nome);
    UserDTO getById(String id);
    Page<UserDTO> getAll(Pageable pageable);
    Usuario save(Usuario user);
    Boolean delete(String id);
}
