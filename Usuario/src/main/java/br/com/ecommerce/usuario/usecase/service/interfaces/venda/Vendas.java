package br.com.ecommerce.usuario.usecase.service.interfaces.venda;

import br.com.ecommerce.usuario.domain.entity.Usuario;
import br.com.ecommerce.usuario.domain.entity.dto.Venda;

import java.util.Optional;

public interface Vendas {
    Optional<Venda> getSalesFromUser(Usuario user);
}
