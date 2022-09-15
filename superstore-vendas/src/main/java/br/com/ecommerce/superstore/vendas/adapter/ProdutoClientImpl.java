package br.com.ecommerce.superstore.vendas.adapter;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "localhost:8080/")
public interface ProdutoClientImpl {

}
