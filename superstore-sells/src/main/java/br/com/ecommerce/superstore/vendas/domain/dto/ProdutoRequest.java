package br.com.ecommerce.superstore.vendas.domain.dto;


import lombok.Getter;

public class ProdutoRequest {
    @Getter
    String productId;
    @Getter
    Integer quantToSell;
}
