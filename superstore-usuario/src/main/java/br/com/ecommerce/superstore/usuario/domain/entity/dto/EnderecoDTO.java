package br.com.ecommerce.superstore.usuario.domain.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import br.com.ecommerce.superstore.usuario.domain.entity.model.Endereco;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "endereco")
@JsonIgnoreProperties(value = "usuario", allowSetters = true)
public class EnderecoDTO {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    protected String id;

    String rua;
    Integer cep;
    Integer numero;
    String complemento;
    String cidade;
    String bairro;
    String estado;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserDTO usuario;

    public static EnderecoDTO transformEnderecoToEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .bairro(endereco.getBairro())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .estado(endereco.getEstado())
                .complemento(endereco.getComplemento())
                .cidade(endereco.getCidade())
                .cep(endereco.getCep()).build();
    }

    public static Set<Endereco> transformEnderecosDTOToEnderecos(Set<EnderecoDTO> enderecos) {
        Set<Endereco> enderecoParaSalvar = new HashSet<>();
        enderecos.stream().forEach(endereco -> {
            var dtoTransformIntoEndereco = Endereco.builder()
                    .bairro(endereco.getBairro())
                    .rua(endereco.getRua())
                    .numero(endereco.getNumero())
                    .estado(endereco.getEstado())
                    .complemento(endereco.getComplemento())
                    .cidade(endereco.getCidade())
                    .cep(endereco.getCep()).build();
            enderecoParaSalvar.add(dtoTransformIntoEndereco);
        });
        return enderecoParaSalvar;
    }

}
