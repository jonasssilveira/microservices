package br.com.ecommerce.usuario.infraestrutura.services;

import br.com.ecommerce.usuario.adapters.user.UserDAOImpl;
import br.com.ecommerce.usuario.domain.entity.Usuario;
import br.com.ecommerce.usuario.domain.entity.dto.EnderecoDTO;
import br.com.ecommerce.usuario.domain.entity.dto.UserDTO;
import br.com.ecommerce.usuario.infraestrutura.exception.exceptions.ConflictException;
import br.com.ecommerce.usuario.usecase.service.UserTransactions;
import br.com.ecommerce.usuario.usecase.service.interfaces.venda.Vendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    final UserDAOImpl userDAOImpl;

    final Vendas vendas;

    final UserTransactions userTransactions;

    public UserService(@Autowired final UserDAOImpl userDAOImpl,
                       @Autowired final Vendas vendas) {
        this.userDAOImpl = userDAOImpl;
        this.vendas = vendas;
        userTransactions = new UserTransactions(
                this.vendas,
                this.userDAOImpl
        );

    }
    @CacheEvict(value = "UserDTO",cacheManager = "cache", allEntries = true)
    @Transactional
     public Boolean createUser(UserDTO userDTO) {
        Usuario user = Usuario.builder().nome(userDTO.getNome())
                .primeiroAcesso(userDTO.getPrimeiroAcesso())
                .cpf(userDTO.getCpf())
                .telefone(userDTO.getTelefone())
                .enderecos(EnderecoDTO.transformEnderecosDTOToEnderecos(userDTO.getEnderecos()))
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        if(!userTransactions.createUser(user))
            throw new ConflictException("Já existe um usuario com este email cadastrado");
        return true;
    }


    @Cacheable(value = "UserDTO",cacheManager = "cache")
    public Page<UserDTO> getAll(Pageable pageable) {
        return userTransactions.getAll(pageable);
    }


    @Cacheable(value = "UserDTO",cacheManager = "cache")
    public UserDTO getById(@PathVariable String id) {
        return userTransactions.getById(id);
    }


    @CacheEvict(value = "UserDTO",cacheManager = "cache", allEntries = true)
    public Boolean deleteUser(@PathVariable String id) {
        return userTransactions.deleteUser(id);
    }


}
