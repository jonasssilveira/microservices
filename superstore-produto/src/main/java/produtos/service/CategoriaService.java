package produtos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import produtos.database.repository.CategoriaRepositoryasdsadsdu;
import produtos.domain.model.Categoria;
import produtos.exceptions.NotFoundException;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepositoryasdsadsdu categoriaRepositoryasdsadsdu;

    public List<Categoria> getAll() {
        return Streamable.of(categoriaRepositoryasdsadsdu.findAll()).map(it-> (Categoria) it).toList();
    }

    public Categoria getById(String id) {
        return categoriaRepositoryasdsadsdu.findById(id).orElseThrow(() -> new NotFoundException("Id nao encontrado"));
    }

    public Categoria getByEmail(String email) {
        return null;
    }

    public Categoria update(Categoria T) {
        return null;
    }

    public Categoria create(Categoria categoria) {
        return categoriaRepositoryasdsadsdu.save(categoria);
    }

    public String delete(String id) {
        return null;
    }

    public Categoria update(Categoria TUpdate, Categoria T) {
        return null;
    }
}
