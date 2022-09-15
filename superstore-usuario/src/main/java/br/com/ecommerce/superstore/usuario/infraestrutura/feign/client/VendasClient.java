package br.com.ecommerce.superstore.usuario.infraestrutura.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("superstore-vendas")
public interface VendasClient {



}
