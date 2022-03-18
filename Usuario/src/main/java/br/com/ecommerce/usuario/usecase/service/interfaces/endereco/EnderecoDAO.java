package br.com.ecommerce.usuario.usecase.service.interfaces.endereco;


import br.com.ecommerce.usuario.domain.entity.Endereco;

import java.util.Set;

public interface EnderecoDAO {
    boolean saveAllEnderecos(Set<Endereco> enderecos);
}
