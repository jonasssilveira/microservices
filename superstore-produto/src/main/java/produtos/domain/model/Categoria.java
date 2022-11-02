package produtos.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "categoria")
@JsonIgnoreProperties(value = "usuario", allowSetters = true)
public class Categoria {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    protected String id;

    @Column
    protected String nome;

    @Getter @Setter
    @ToString.Include
    @OneToMany
    List<Produto> produtos;

    @Override
    public String toString() {
        return "Categoria{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", produtos=" + produtos +
                '}';
    }
}
