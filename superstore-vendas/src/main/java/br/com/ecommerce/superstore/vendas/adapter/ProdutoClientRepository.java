package br.com.ecommerce.superstore.vendas.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "localhost:8080/")
@Component
public interface ProdutoClientRepository {

    @PatchMapping(value = "sell/{id}/{quantToSell}")
    Boolean sell(@PathVariable("id") String id,@PathVariable("quantToSell")Integer quantToSell);

}
