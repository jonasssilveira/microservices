package br.com.ecommerce.superstore.vendas.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(url = "localhost:8080/")
@Component
public interface ProdutoClientImpl {

}
