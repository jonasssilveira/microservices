package br.com.ecommerce.superstore.vendas.infraestrutura.repository;

import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends CrudRepository<Venda, String> {
    Page<Venda> findAll(Pageable pageable);
    List<Venda> findAllByUserId(String id);
    List<Venda> findByUserIdAndFechado(String id, Boolean fe);
    Optional<Venda> findById(String id);
    List<Venda> findVendaByFechado(Boolean fechado);
}
