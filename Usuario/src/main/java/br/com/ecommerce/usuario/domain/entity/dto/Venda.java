package br.com.ecommerce.usuario.domain.entity.dto;

import br.com.ecommerce.usuario.domain.entity.Usuario;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Venda {

    private Usuario user;
    private LocalDate dateCreated;

    private LocalDate buyDate;

    private Boolean fechado;
}
