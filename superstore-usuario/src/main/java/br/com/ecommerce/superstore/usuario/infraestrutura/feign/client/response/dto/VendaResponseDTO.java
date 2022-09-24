package br.com.ecommerce.superstore.usuario.infraestrutura.feign.client.response.dto;

import br.com.ecommerce.superstore.usuario.domain.entity.model.Usuario;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VendaResponseDTO {

    private Usuario user;
    private LocalDate dateCreated;

    private LocalDate buyDate;

    private Boolean fechado;

}
