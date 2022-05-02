package br.com.ecommerce.superstore.usuario.infraestrutura.services;

import br.com.ecommerce.superstore.usuario.adapters.kafka.Kafka;
import br.com.ecommerce.superstore.usuario.domain.entity.Usuario;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.EnderecoDTO;
import br.com.ecommerce.superstore.usuario.usecase.UserTransactions;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.venda.Vendas;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.UserDTO;
import br.com.ecommerce.superstore.usuario.infraestrutura.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import br.com.ecommerce.superstore.usuario.adapters.user.UserDAOImpl;

@Service
@Transactional
public class UserService {
    final UserDAOImpl userDAOImpl;

    final Vendas vendas;

    final UserTransactions userTransactions;
    final Kafka kafka;
    public UserService(@Autowired final UserDAOImpl userDAOImpl,
                       @Autowired final Vendas vendas) {
        this.userDAOImpl = userDAOImpl;
        this.vendas = vendas;
        this.kafka = new Kafka();
        userTransactions = new UserTransactions(
                this.vendas,
                this.userDAOImpl,
                this.kafka);

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
