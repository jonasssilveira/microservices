package br.com.ecommerce.superstore.vendas.domain.interfaces;

import br.com.ecommerce.superstore.vendas.domain.dto.VendaDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VendaDAO {

    Boolean pedidoToVenda(Venda venda);

    Boolean createVenda(Venda venda);

    Optional<Venda> getVendaByUserId(String userId);

    Venda getVendaById(String id);

}
