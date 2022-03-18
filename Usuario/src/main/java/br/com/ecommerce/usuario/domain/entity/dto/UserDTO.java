package br.com.ecommerce.usuario.domain.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "usuario")
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserDTO{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    protected String id;

    protected String nome;

    private String email;

    private String password;

    private String cpf;

    private String telefone;

    private Boolean primeiroAcesso;

    @OneToMany(mappedBy ="usuario")
    Set<EnderecoDTO> enderecos;

    public UserDTO() {
        this.id = UUID.randomUUID().toString();
    }

}
