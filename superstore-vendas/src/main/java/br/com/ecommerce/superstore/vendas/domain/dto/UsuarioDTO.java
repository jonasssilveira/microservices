package br.com.ecommerce.superstore.vendas.domain.dto;


public record UsuarioDTO(String id,
                         String email,
                         String nome,
                         String cpf,
                         String telefone,
                         Boolean primeiroAcesso) {
}
