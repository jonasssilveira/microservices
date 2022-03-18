package br.com.ecommerce.superstore.vendas.domain.entities;

import java.math.BigDecimal;

public record Produto(String nome,
                      String descricao,
                      BigDecimal valor,
                      Integer quantidade
) {

}
