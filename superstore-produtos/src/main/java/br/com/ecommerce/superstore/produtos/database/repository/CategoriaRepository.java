package br.com.ecommerce.superstore.produtos.database.repository;

import br.com.ecommerce.superstore.produtos.domain.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, String> {
}
