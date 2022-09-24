package br.com.ecommerce.superstore.usuario.infraestrutura.feign.client;

import br.com.ecommerce.superstore.usuario.infraestrutura.feign.client.response.dto.VendaResponseDTO;
import br.com.ecommerce.superstore.usuario.usecase.interfaces.venda.VendasClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("superstore-vendas")
public interface VendasRepository extends VendasClient {

    @GetMapping("/superstore/v1/vendas/all/user/{userId}")
    List<VendaResponseDTO> getAllPedidosByUser(@PathVariable("userId") String userId);

}
