package br.com.ecommerce.superstore.vendas.domain.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "venda")
public class Venda {

    @Getter
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    protected String id;

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

    @Getter
    @Setter
    @Column
    private String userId;

    @Getter
    @Setter
    @Column
    private String produtoId;

}


