package br.com.ecommerce.superstore.usuario.adapters.user;


import br.com.ecommerce.superstore.usuario.adapters.kafka.Kafka;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.UserDTO;
import br.com.ecommerce.superstore.usuario.domain.entity.model.Usuario;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ecommerce.superstore.usuario.adapters.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDAOImpl implements UserDAO {

    @Autowired
    UserRepository userRepository;

    Kafka kafka;

    @Override
    public Optional<Usuario> getByEmail(String email) {
        Optional<UserDTO> userDTOOptional = userRepository.getByEmail(email).or(() -> Optional.empty());
        if(userDTOOptional.isEmpty())
            return Optional.empty();

        return Optional.of(Usuario.builder()
                .email(userDTOOptional.get().getEmail())
                .cpf(userDTOOptional.get().getCpf())
                .nome(userDTOOptional.get().getNome())
                .primeiroAcesso(userDTOOptional.get().getPrimeiroAcesso())
                .telefone(userDTOOptional.get().getTelefone())
                .build());
    }

    @Override
    public UserDTO getById(String id) {
        return userRepository.getById(id).get();
    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Usuario save(Usuario user) {
        UserDTO newUser = new UserDTO();
        newUser.setCpf(user.getCpf());
        newUser.setEmail(user.getEmail());
        newUser.setNome(user.getNome());
        newUser.setTelefone(user.getTelefone());
        newUser.setPrimeiroAcesso(true);
        UserDTO userDTO = userRepository.save(newUser);

        return Usuario.builder()
                .email(userDTO.getEmail())
                .telefone(userDTO.getTelefone())
                .nome(userDTO.getNome())
                .cpf(userDTO.getCpf())
                .primeiroAcesso(userDTO.getPrimeiroAcesso())
                .build();
    }

    @Override
    public Boolean delete(String id) {
        userRepository.delete(getById(id));
        return true;
    }

    @Override
    public Optional<Usuario> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }
}
