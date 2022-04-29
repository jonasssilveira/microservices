package br.com.ecommerce.vendas;

import br.com.ecommerce.superstore.vendas.domain.entities.Produto;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.domain.interfaces.ProdutoClient;
import br.com.ecommerce.superstore.vendas.domain.interfaces.VendaDAO;
import br.com.ecommerce.superstore.vendas.domain.usecase.VendaTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    VendaTransaction transactionsOfSells;

    List<Produto> productsStockless;

    List<Produto> productsInStock;

    Venda vendaAberta;

    Venda vendaFechada;

    @BeforeEach
    public void setup() {
        vendaDAO = mock(VendaDAO.class);

        produtoClient = mock(ProdutoClient.class);

        productsStockless = new ArrayList<>();

        productsInStock = new ArrayList<>();

        productsStockless.add(new Produto("Papelex",
                "Papel higienico",
                new BigDecimal("100"),
                0));

        transactionsOfSells = new VendaTransaction(vendaDAO,
                produtoClient);

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
    void shouldSellBecauseThereAreProductsAndSellIsClosed() {
        //arrange
        when(produtoClient.verifyIfSomeProductIsInFalt(anyString())).thenReturn(productsInStock);
        when(vendaDAO.getVendaByUserId(anyString())).thenReturn(Optional.of(vendaFechada));

        //act
        var transactionResult = transactionsOfSells.sell("123456");

        //assert
        assertTrue(transactionResult);

    }

    @Test
    void shouldNotSellBecauseThereAreASellOpen() {
        //arrange
        when(produtoClient.verifyIfSomeProductIsInFalt(anyString())).thenReturn(productsStockless);
        when(vendaDAO.getVendaByUserId(anyString())).thenReturn(Optional.of(vendaAberta));


        //act
       assertThrows(NoSuchElementException.class,()->transactionsOfSells.sell("123456"));

    }

    @Test
    void shouldCreateSellBecauseThereAreNoSellSaved() {
        //arrange
        when(produtoClient.verifyIfSomeProductIsInFalt(anyString())).thenReturn(productsStockless);
        when(vendaDAO.getVendaByUserId(anyString())).thenReturn(Optional.empty());

        //act
        transactionsOfSells.getVendaByUserId("123456");

    }

}
