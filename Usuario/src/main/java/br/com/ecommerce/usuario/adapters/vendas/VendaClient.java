package br.com.ecommerce.usuario.adapters.vendas;

import br.com.ecommerce.usuario.domain.entity.Usuario;
import br.com.ecommerce.usuario.usecase.service.interfaces.venda.Vendas;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VendaClient implements Vendas {

    @Override
    public Optional<br.com.ecommerce.usuario.domain.entity.dto.Venda> getSalesFromUser(Usuario user) {
        return Optional.empty();
    }
}
