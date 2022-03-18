package br.com.ecommerce.superstore.vendas.domain.interfaces;

import br.com.ecommerce.superstore.vendas.domain.dto.UsuarioDTO;

import java.util.Optional;

public interface UsuarioClient {
    Optional<UsuarioDTO> getUsuario(String idUsuario);
}
