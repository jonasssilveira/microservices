package br.com.ecommerce.superstore.produtos.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    protected String id;

    @Column
    @NotBlank(message = "Nome não pode ser vazio!")
    protected String nome;

    @Getter @Setter
    @ToString.Include
    @OneToMany
    List<Produto> produtos;
}
