package br.com.ecommerce.usuario.domain.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Endereco{

    String rua = "";
    Integer cep = 00000;
    Integer numero = 0000;
    String complemento = "";
    String cidade = "";
    String bairro = "";
    String estado = "";

}
