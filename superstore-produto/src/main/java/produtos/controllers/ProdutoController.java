package produtos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import produtos.domain.model.Produto;
import produtos.service.ProdutoService;
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
    public Produto getProdutoByNome(@PathVariable("nome") String nome){
        return produtoService.findProdutoByName(nome);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Produto getProdutoById(@PathVariable("id") String id){
        return produtoService.getProdutoById(id);
    }

    @PatchMapping("/admin")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Produto updateProduto(@RequestBody Produto produto){
        return produtoService.updateProduto(produto);
    }

    @PostMapping("/admin")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Produto createProduto(@RequestBody Produto produto){
        return produtoService.createProduto(produto);
    }

    @DeleteMapping("/admin/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduto(@PathVariable("id") String id){
        return produtoService.deleteProduto(id);
    }

    @PatchMapping("buy/{id}/{quantToBuy}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> buy(@PathVariable("id") String id, @PathVariable("quantToBuy") Integer quantToBuy ){
        produtoService.buyProduct(id,quantToBuy);
        return ResponseEntity.ok(true);
    }
    @PatchMapping("sell/{id}/{quantToSell}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> sell(@PathVariable("id") String id, @PathVariable("quantToSell") Integer quantToSell ){
        produtoService.sellProduct(id,quantToSell);
        return ResponseEntity.ok(true);
    }

}
