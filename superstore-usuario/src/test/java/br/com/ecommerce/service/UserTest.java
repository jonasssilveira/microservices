package br.com.ecommerce.service;

import br.com.ecommerce.superstore.usuario.adapters.kafka.Kafka;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.EmailDTO;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.ecommerce.superstore.usuario.domain.entity.model.Usuario;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.Venda;
import br.com.ecommerce.superstore.usuario.usecase.UserTransactions;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.user.UserDAO;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.venda.Vendas;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserTest {

    private Usuario user;
    private UserTransactions userTransactions;
    private Vendas vendas;
    private UserDAO userDAO;
    private Kafka kafka;
    @BeforeEach
    void setup(){
        vendas = mock(Vendas.class);
        userDAO = mock(UserDAO.class);
        kafka = mock(Kafka.class);
        user = Usuario.builder().email("jonas").id("123456").build();
        userTransactions = new UserTransactions(vendas, userDAO, kafka);

    }

    @Test
    @DisplayName("Deve executar a exclusão do usuario caso não encontre uma venda relacionada a ele")
    void mustDeleteUserWhenThereIsNotASaleWithThatUser(){
        //arrange
        when(vendas.getSalesFromUser(user)).thenReturn(Optional.empty());
        when(userDAO.getById(anyString())).thenReturn(new UserDTO());
        when(userDAO.delete(anyString())).thenReturn(true);

        //act
        Boolean userDeleted = userTransactions.deleteUser(user.getId());

        //assert
        assertTrue(userDeleted);

    }
    @Test
    @DisplayName("Não deve executar a exclusão do usuario caso encontre uma venda relacionada a ele")
    void mustNotDeleteUserWhenThereIsASaleWithThatUser(){
        //arrange
        when(vendas.getSalesFromUser(user)).thenReturn(Optional.of(new Venda()));
        when(userDAO.getById(anyString())).thenReturn(new UserDTO());
        when(userDAO.delete(anyString())).thenReturn(false);

        //act
        Boolean userDeleted = userTransactions.deleteUser(user.getId());

        //assert
        assertFalse(userDeleted);

    }

    @Test
    @DisplayName("Não deve executar a criação do usuario caso encontre um usuario com o mesmo email")
    void mustNotCreateUserWhenThereIsAnotherUserWithSameEmail(){
        //arrange
        when(this.userDAO.getByEmail("jonas")).thenReturn(Optional.of(new Usuario()));

        //act
        Boolean userCreated = userTransactions.createUser(user);

        //assert
        assertFalse(userCreated);

    }

    @Test
    @DisplayName("Deve executar a criação do usuario caso não encontre outro com o mesmo email")
    void mustCreateUserWhenThereIsAnotherUserWithSameEmail(){
        //arrange
        when(userDAO.getByEmail(anyString())).thenReturn(Optional.empty());

        //act
        Boolean userDeleted = userTransactions.createUser(user);

        //assert
        assertTrue(userDeleted);

    }

    @Test
    void testeSomething(){
        //arrange
        user.setPrimeiroAcesso(true);
        when(userDAO.findByEmailAndPassword(user.getEmail(),
                user.getPassword())).thenReturn(Optional.of(user));
        EmailDTO email = new EmailDTO(user.getEmail(), "", "");
        //act
        userTransactions.login(user.getEmail(), user.getPassword());
        //verify
        verify(kafka, times(1)).send(email);
    }

    @Test
    void testeSomething1(){
        //arrange
        user.setPrimeiroAcesso(false);
        when(userDAO.findByEmailAndPassword(user.getEmail(),
                user.getPassword())).thenReturn(Optional.of(user));
        EmailDTO email = new EmailDTO(user.getEmail(), "", "");
        //act
        userTransactions.login(user.getEmail(), user.getPassword());
        //verify
        verify(kafka, times(0)).send(email);
    }

}