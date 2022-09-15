package br.com.ecommerce.superstore.usuario.adapters.repository;

import br.com.ecommerce.superstore.usuario.domain.entity.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.ecommerce.superstore.usuario.domain.entity.model.Usuario;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<br.com.ecommerce.superstore.usuario.domain.entity.dto.UserDTO, String> {
    Optional<UserDTO> getByEmail(String email);
    Optional<UserDTO> getById(String id);
    Page<UserDTO> findAll(Pageable paginacao);
    Optional<Usuario> findByEmailAndPassword(String email, String password);
 }
