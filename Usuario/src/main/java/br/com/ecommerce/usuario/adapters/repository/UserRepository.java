package br.com.ecommerce.usuario.adapters.repository;

import br.com.ecommerce.usuario.domain.entity.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDTO, String> {
    Optional<UserDTO> getByEmail(String email);
    Optional<UserDTO> getById(String id);
    Page<UserDTO> findAll(Pageable paginacao);
 }
