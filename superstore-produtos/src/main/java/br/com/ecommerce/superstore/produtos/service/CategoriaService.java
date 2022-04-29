package br.com.ecommerce.superstore.produtos.service;

import br.com.ecommerce.superstore.produtos.exceptions.NotFoundException;
import br.com.ecommerce.superstore.produtos.domain.model.Categoria;
import br.com.ecommerce.superstore.produtos.database.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;


    public List<Categoria> getAll() {
        return Streamable.of(categoriaRepository.findAll()).map(it-> (Categoria) it).toList();
    }

    public Categoria getById(String id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException("Id nao encontrado"));
    }

    public Categoria getByEmail(String email) {
        return null;
    }

    public Categoria update(Categoria T) {
        return null;
    }

    public Categoria create(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public String delete(String id) {
        return null;
    }

    public Categoria update(Categoria TUpdate, Categoria T) {
        return null;
    }
}
