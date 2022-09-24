package br.com.ecommerce.superstore.vendas.domain.interfaces;

import br.com.ecommerce.superstore.vendas.domain.dto.VendaDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface VendaDAO {

    Boolean pedidoToVenda(Venda venda);

    Boolean createVenda(Venda venda);

    Optional<VendaDTO> getVendaByUserId(String userId);

    VendaDTO getVendaById(String id);

}
