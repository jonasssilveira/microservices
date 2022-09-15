package br.com.ecommerce.superstore.vendas.infraestrutura.repository;

import br.com.ecommerce.superstore.vendas.domain.dto.VendaDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends CrudRepository<Venda, String> {
    Page<VendaDTO> findAll(Pageable pageable);
    List<VendaDTO> findAllById(String id);
    Optional<Venda> findById(String id);
}
