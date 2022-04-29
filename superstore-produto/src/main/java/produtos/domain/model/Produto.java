package produtos.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "produto")
@JsonIgnoreProperties(value = "usuario", allowSetters = true)
public class Produto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    protected String id;
    @Column
    protected String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column
    private BigDecimal valor;

    @Column
    private Integer quantidade;

    @Column(columnDefinition = "TEXT")
    private String imagemProduto;

    @JsonIgnore
    @ManyToOne
    Categoria categoria;

}
