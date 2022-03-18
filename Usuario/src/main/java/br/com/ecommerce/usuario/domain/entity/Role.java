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
public class Role {

    private Integer peso;
    private String id;
    private String nome;
}
