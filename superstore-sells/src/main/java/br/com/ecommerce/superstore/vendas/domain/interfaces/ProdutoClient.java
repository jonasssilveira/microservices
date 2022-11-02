package br.com.ecommerce.superstore.vendas.domain.interfaces;

import br.com.ecommerce.superstore.vendas.domain.entities.Produto;

import java.util.List;

public interface ProdutoClient {
    List<Produto> verifyIfSomeProductIsInFalt(String id);
}
