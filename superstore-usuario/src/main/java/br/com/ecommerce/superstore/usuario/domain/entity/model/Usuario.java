package br.com.ecommerce.superstore.usuario.domain.entity.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {

    private String id;

    private String email;

    private String nome;

    private String password;

    private String cpf;

    private String telefone;

    private Boolean primeiroAcesso;

    private Set<Endereco> enderecos;

}