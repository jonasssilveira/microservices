package br.com.ecommerce.superstore.vendas.domain.usecase;

import br.com.ecommerce.superstore.vendas.domain.entities.Produto;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.domain.interfaces.ProdutoClient;
import br.com.ecommerce.superstore.vendas.domain.interfaces.VendaDAO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public record VendaTransaction(VendaDAO vendaDAO,
                               ProdutoClient produtoClient) {

    public Boolean sell(String id) throws NoSuchElementException {
        Optional<Venda> vendaByUserId = getVendaByUserId(id);
        vendaByUserId.get().sell();
        vendaDAO.createVenda(vendaByUserId.get());

        return true;
    }

    public Optional<Venda> getVendaByUserId(String id) {
        Optional<Venda> vendaByUserId = vendaDAO.getVendaByUserId(id);
        List<Produto> produtosFromSales = produtoClient.verifyIfSomeProductIsInFalt(id);
        if ((vendaByUserId.isEmpty()
                || !vendaByUserId.get().getFechado()) && !produtosFromSales.isEmpty())
            return Optional.empty();

        return vendaByUserId;
    }
}
