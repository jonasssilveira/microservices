package br.com.ecommerce.superstore.usuario.domain.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Endereco{

    String rua;
    Integer cep;
    Integer numero;
    String complemento;
    String cidade;
    String bairro;
    String estado;

}
