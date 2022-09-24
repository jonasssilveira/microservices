package br.com.ecommerce.superstore.usuario.usecase.interfaces.venda;

import br.com.ecommerce.superstore.usuario.infraestrutura.feign.client.response.dto.VendaResponseDTO;

import java.util.List;
import java.util.Optional;

public interface VendasClient {
    List<VendaResponseDTO> getAllPedidosByUser(String userId);
}
