package br.com.ecommerce.superstore.vendas.domain.dto;

import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "venda")
public class VendaDTO {

    @Getter
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    protected String id;

    @Getter
    @Setter
    private String user_id;

    @Getter
    @Setter
    @Column
    private LocalDate dateCreated;

    @Getter
    @Setter
    @Column
    private LocalDate buyDate;

    @Getter
    @Setter
    @Column
    private Boolean fechado;

    public static VendaDTO vendaTOVendaDTO(Venda venda) {
        return new VendaDTO(venda.getId(),
                venda.getId(),
                venda.getDateCreated(),
                venda.getBuyDate(),
                venda.getFechado());
    }

    public static Venda vendaDTOTOVenda(VendaDTO vendaDTO) {
        return new Venda(vendaDTO.getDateCreated(),
                vendaDTO.getBuyDate(),vendaDTO.getFechado(),
                vendaDTO.getId(), vendaDTO.getUser_id());
    }

}
