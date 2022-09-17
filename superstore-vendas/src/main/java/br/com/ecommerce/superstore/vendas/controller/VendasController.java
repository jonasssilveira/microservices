package br.com.ecommerce.superstore.vendas.controller;

import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.infraestrutura.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vendas/")
public class VendasController {
    VendaService vendaService;

    VendasController(@Autowired VendaService vendaService){
        this.vendaService = vendaService;
        ;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Venda> listVendas(){
        return vendaService.listVendas();
    }

    @PostMapping("open/user/{userId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Venda> getAllPedidosByUser(@PathVariable("userId") String userId){
        return vendaService.listByUser(userId);
    }

    @PostMapping("close/user/{userId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Venda> getVendasByUser(@PathVariable("userId") String userId){
        return vendaService.getVendaFechadaPorUsuario(userId);
    }

    @PostMapping("admin/pedidos/all")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Venda> getPedidos(){
        return vendaService.getPedidos();
    }

    @PostMapping("/produto/{idProduto}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<Venda> getAllPedidosByProduto(@PathVariable("idProduto") String idProduto, @PageableDefault(sort = "nome",
            direction = Sort.Direction.ASC, page = 0, size = 20) Pageable paginacao){
        return vendaService.listByProduto(idProduto,paginacao);
    }
    @GetMapping("/id/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Venda getVendaById(@PathVariable("id") String id){
        return vendaService.getVendaById(id);
    }

    @PatchMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Venda updateVenda(@RequestBody Venda venda){
        return vendaService.updateVenda(venda);
    }

    @PatchMapping("/finalizar-compra")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Venda compraEfetuada(@RequestBody Venda venda){
        return vendaService.compraEfetuada(venda);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Venda createVenda(@RequestBody Venda venda){
        return vendaService.createVenda(venda);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String deleteVenda(@PathVariable("id")  String id){
        return vendaService.deleteVenda(id);
    }

}
