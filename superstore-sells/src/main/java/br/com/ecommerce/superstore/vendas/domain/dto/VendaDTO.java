package br.com.ecommerce.superstore.vendas.domain.dto;

import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class VendaDTO {
    @Getter
    private LocalDate dateCreated;
    @Getter
    private LocalDate buyDate;
    @Getter
    @Setter
    private Boolean fechado;
    @Getter
    private String id;
    @Getter
    private String usuarioId;

    @Getter
    private List<ProdutoRequest> produtoRequests;

    public VendaDTO(String id,
                    LocalDate dateCreated,
                    LocalDate buyDate,
                    Boolean fechado,
                    String usuarioId) {

        this.dateCreated = dateCreated;
        this.buyDate = buyDate;
        this.fechado = fechado;
        this.id = id;
        this.usuarioId = usuarioId;
    }


    public static VendaDTO vendaTOVendaDTO(Venda venda) {
        return new VendaDTO(venda.getId(),
                venda.getDateCreated(),
                venda.getBuyDate(),
                venda.getFechado(),
                venda.getId());
    }

    public static Venda vendaDTOTOVenda(VendaDTO vendaDTO) {
        return new Venda(vendaDTO.getId(),
                vendaDTO.getDateCreated(),
                vendaDTO.getBuyDate(),
                vendaDTO.getFechado(),
                vendaDTO.getUsuarioId());
    }

}