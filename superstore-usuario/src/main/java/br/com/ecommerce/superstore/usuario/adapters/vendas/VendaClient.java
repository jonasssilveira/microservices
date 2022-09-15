package br.com.ecommerce.superstore.usuario.adapters.vendas;

import br.com.ecommerce.superstore.usuario.domain.entity.model.Usuario;
import br.com.ecommerce.superstore.usuario.domain.entity.dto.Venda;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.venda.Vendas;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VendaClient implements Vendas {

    @Override
    public Optional<Venda> getSalesFromUser(Usuario user) {
        return Optional.empty();
    }
}
