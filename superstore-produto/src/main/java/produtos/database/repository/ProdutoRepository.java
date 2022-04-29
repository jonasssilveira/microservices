package produtos.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import produtos.domain.model.Produto;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, String> {

    Optional<Produto> findByNome(String nome);
}
