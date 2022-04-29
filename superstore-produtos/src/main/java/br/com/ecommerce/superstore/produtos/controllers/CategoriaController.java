package br.com.ecommerce.superstore.produtos.controllers;

import br.com.ecommerce.superstore.produtos.domain.model.Categoria;
import br.com.ecommerce.superstore.produtos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Categoria> getAll() {
        return categoriaService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Categoria getById(String id) {
        return categoriaService.getById(id);
    }

    public Categoria getByEmail(String email) {
        return null;
    }

    public Categoria update(Categoria T) {
        return null;
    }

    public Categoria create(Categoria T) throws IOException, ExecutionException, InterruptedException {
        return null;
    }

    public String delete(String id) {
        return null;
    }

    public Categoria update(Categoria TUpdate, Categoria T) {
        return null;
    }
}
