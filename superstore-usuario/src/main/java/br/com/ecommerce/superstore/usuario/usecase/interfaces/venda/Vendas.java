package br.com.ecommerce.superstore.usuario.usecase.interfaces.venda;

import br.com.ecommerce.superstore.usuario.domain.entity.model.Usuario;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.Venda;
import java.util.Optional;

public interface Vendas {
    Optional<Venda> getSalesFromUser(Usuario user);
}
