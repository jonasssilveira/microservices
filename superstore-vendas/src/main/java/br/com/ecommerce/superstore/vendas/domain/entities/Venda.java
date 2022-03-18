package br.com.ecommerce.superstore.vendas.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Venda {
    @Getter
    private LocalDate dateCreated;
    @Getter
    private LocalDate buyDate;
    @Getter @Setter
    private Boolean fechado;
    @Getter
    private String id;
    @Getter
    private String usuarioId;

    public Venda(LocalDate dateCreated,
                 LocalDate buyDate,
                 Boolean fechado,
                 String id,
                 String usuarioId) {

        this.dateCreated = dateCreated;
        this.buyDate = buyDate;
        this.fechado = fechado;
        this.id = id;
        this.usuarioId = usuarioId;
    }

    public void sell() {
        this.fechado = true;
    }

}
