package br.com.ecommerce.superstore.vendas.infraestrutura.services;

import br.com.ecommerce.superstore.vendas.adapter.VendaDAOImpl;
import br.com.ecommerce.superstore.vendas.domain.dto.VendaDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.domain.usecase.VendaTransaction;
import br.com.ecommerce.superstore.vendas.infraestrutura.exception.NotFoundException;
import br.com.ecommerce.superstore.vendas.infraestrutura.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    VendaTransaction vendaTransaction;
    VendaRepository vendaRepository;
    VendaService(@Autowired VendaDAOImpl vendaDAO,
                 @Autowired VendaRepository vendaRepository){
        vendaTransaction = new VendaTransaction(vendaDAO,
                null);
        this.vendaRepository = vendaRepository;
    }

    @Cacheable(value = "VendaDTO",cacheManager = "cache")
    public Page<VendaDTO> getAll(Pageable pageable){
        return this.vendaRepository.findAll(pageable);
    }

    public VendaDTO getById(String id){
        return this.vendaRepository.findById(id).get();
    }

    public ResponseEntity<Boolean> sell(String id){
        return ResponseEntity.ok(this.vendaTransaction.sell(id));
    }

}
