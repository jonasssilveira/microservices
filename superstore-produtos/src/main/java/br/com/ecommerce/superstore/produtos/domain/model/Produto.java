package br.com.ecommerce.superstore.produtos.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    protected String id;

    @Column
    @NotBlank(message = "Nome não pode ser vazio!")
    protected String nome;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Descrição não pode ser nula")
    private String descricao;

    @Column
    @NotBlank(message = "Valor não pode ser nulo")
    private BigDecimal valor;

    @Column
    @NotBlank(message = "Quantidade não pode ser nulo")
    private Integer quantidade;

    @Column(columnDefinition = "TEXT")
    private String imagemProduto;

    @JsonIgnore
    @ManyToOne
    Categoria categoria;

    public Produto(String nome, String descricao, BigDecimal valor, Integer quantidade, String imagemProduto) {
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.imagemProduto = imagemProduto;
    }

    public Produto() {

    }
}
