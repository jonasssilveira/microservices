package br.com.ecommerce.superstore.produtos.controllers;

import br.com.ecommerce.superstore.produtos.domain.model.Produto;
import br.com.ecommerce.superstore.produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> listProdutos(){
        return produtoService.listProdutos();
    }

    @GetMapping("/nome/{nome}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Produto getProdutoByNome(@PathVariable("nome") @Valid String nome){
        return produtoService.findProdutoByName(nome);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Produto getProdutoById(@PathVariable("id") @Valid String id){
        return produtoService.getProdutoById(id);
    }

    @PatchMapping("/admin")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Produto updateProduto(@RequestBody @Valid Produto produto){
        return produtoService.updateProduto(produto);
    }

    @PostMapping("/admin")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Produto createProduto(@RequestBody @Valid Produto produto){
        return produtoService.createProduto(produto);
    }

    @DeleteMapping("/admin/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduto(@PathVariable("id")  @Valid String id){
        return produtoService.deleteProduto(id);
    }

}
