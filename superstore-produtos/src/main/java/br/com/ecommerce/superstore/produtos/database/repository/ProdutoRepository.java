package br.com.ecommerce.superstore.produtos.database.repository;

import br.com.ecommerce.superstore.produtos.domain.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, String> {

    Optional<Produto> findByNome(String nome);
}
