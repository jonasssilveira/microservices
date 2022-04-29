package br.com.ecommerce.superstore.vendas.infraestrutura.repository;

import br.com.ecommerce.superstore.vendas.domain.dto.VendaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendaRepository extends CrudRepository<VendaDTO, String> {
    Page<VendaDTO> findAll(Pageable pageable);
    Optional<VendaDTO> findAllById(String id);
    Optional<VendaDTO> findById(String id);
}
