package br.com.ecommerce.vendas;

import br.com.ecommerce.superstore.vendas.domain.dto.UsuarioDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Produto;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.domain.interfaces.ProdutoClient;
import br.com.ecommerce.superstore.vendas.domain.interfaces.UsuarioClient;
import br.com.ecommerce.superstore.vendas.domain.interfaces.VendaDAO;
import br.com.ecommerce.superstore.vendas.domain.usecase.VendaTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.rmi.NoSuchObjectException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VendaTransactionTest {

    @Mock
    VendaDAO vendaDAO;

    @Mock
    ProdutoClient produtoClient;

    @Mock
    UsuarioClient usuarioClient;

    UsuarioDTO usuario;

    VendaTransaction transactionsOfSells;

    Produto productInStock;

    Produto productNotInStock;

    Venda vendaAberta;

    Venda vendaFechada;

    @BeforeEach
    public void setup() {
        vendaDAO = mock(VendaDAO.class);
        produtoClient = mock(ProdutoClient.class);
        usuarioClient = mock(UsuarioClient.class);
        productInStock = new Produto("Papelex",
                "Papel higienico",
                new BigDecimal("100"),
                20);

        productNotInStock = new Produto("Monark",
                "Não o nazista",
                BigDecimal.ZERO,
                20);

        transactionsOfSells = new VendaTransaction(vendaDAO,
                produtoClient,
                usuarioClient);

        usuario = new UsuarioDTO("123456",
                "jonas@gmail.com",
                "jonas",
                "123456",
                "22222",
                true);

        vendaAberta = new Venda(LocalDate.now(), LocalDate.now(),
                false,
                "2343213",
                "234444");

        vendaFechada = new Venda(LocalDate.now(), LocalDate.now(),
                true,
                "2343213",
                "234444");

    }

    @Test
    void shouldSellBecauseThereAreProductsAndSellIsClosed() throws NoSuchObjectException {
        //arrange
        when(produtoClient.getProduto(anyString())).thenReturn(productInStock);
        when(usuarioClient.getUsuario(anyString())).thenReturn(Optional.of(usuario));
        when(vendaDAO.getVendaByUserId(anyString())).thenReturn(Optional.of(vendaFechada));

        //act
        var transactionResult = transactionsOfSells.sell("123456");

        //assert
        assertTrue(transactionResult);

    }

    @Test
    void shouldNotSellBecauseThereAreASellOpen() throws NoSuchObjectException {
        //arrange
        when(produtoClient.getProduto(anyString())).thenReturn(productInStock);
        when(usuarioClient.getUsuario(anyString())).thenReturn(Optional.of(usuario));
        when(vendaDAO.getVendaByUserId(anyString())).thenReturn(Optional.of(vendaAberta));


        //act
        var transactionResult = transactionsOfSells.sell("123456");

        //assert
        assertTrue(transactionResult);

    }

    @Test
    void shouldNotSellBecauseThereAreProductsButNoUser() {
        //arrange
        when(produtoClient.getProduto(anyString())).thenReturn(productInStock);
        when(usuarioClient.getUsuario(anyString())).thenReturn(Optional.empty());

        //act
        assertThrows(NoSuchObjectException.class,
                () -> transactionsOfSells.sell("123456"));
    }

    @Test
    void shouldNotCreateSellBecauseThereAreAnotherSellToThatUser() throws NoSuchObjectException {
        //arrange
        when(produtoClient.getProduto(anyString())).thenReturn(productNotInStock);
        when(usuarioClient.getUsuario(anyString())).thenReturn(Optional.empty());

        //act
        assertThrows(NoSuchObjectException.class, () -> transactionsOfSells.getVendaByUserId("123456"));

    }

    @Test
    void shouldCreateSellBecauseThereAreNoSellSaved() throws NoSuchObjectException {
        //arrange
        when(produtoClient.getProduto(anyString())).thenReturn(productNotInStock);
        when(usuarioClient.getUsuario(anyString())).thenReturn(Optional.of(usuario));
        when(vendaDAO.getVendaByUserId(anyString())).thenReturn(Optional.empty());

        //act
        transactionsOfSells.getVendaByUserId("123456");

    }

}
