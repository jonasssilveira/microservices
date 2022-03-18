package br.com.ecommerce.superstore.usuario.usecase.interfaces.endereco;

import br.com.ecommerce.superstore.usuario.domain.entity.Endereco;
import java.util.Set;

public interface EnderecoDAO {
    boolean saveAllEnderecos(Set<Endereco> enderecos);
}
