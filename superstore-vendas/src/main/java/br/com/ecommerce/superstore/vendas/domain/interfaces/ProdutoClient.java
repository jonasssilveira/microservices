package br.com.ecommerce.superstore.vendas.domain.interfaces;

import br.com.ecommerce.superstore.vendas.domain.entities.Produto;

public interface ProdutoClient {
    Produto getProduto(String id);
}
