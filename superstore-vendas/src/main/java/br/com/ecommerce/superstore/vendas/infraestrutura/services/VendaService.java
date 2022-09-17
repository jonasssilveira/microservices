package br.com.ecommerce.superstore.vendas.infraestrutura.services;

import br.com.ecommerce.superstore.vendas.adapter.ProdutoClientImpl;
import br.com.ecommerce.superstore.vendas.adapter.VendaDAOImpl;
import br.com.ecommerce.superstore.vendas.domain.dto.VendaDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Produto;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.domain.interfaces.ProdutoClient;
import br.com.ecommerce.superstore.vendas.domain.usecase.VendaTransaction;
import br.com.ecommerce.superstore.vendas.infraestrutura.exception.BadRequestException;
import br.com.ecommerce.superstore.vendas.infraestrutura.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    VendaDAOImpl vendaDAO;
    VendaTransaction vendaTransaction;
    VendaRepository vendaRepository;

    ProdutoClientImpl produtoClient;
    VendaService(@Autowired VendaDAOImpl vendaDAO,
                 @Autowired VendaRepository vendaRepository,
                 @Autowired ProdutoClientImpl produtoClient){
        this.vendaDAO = vendaDAO;
        this.vendaTransaction = new VendaTransaction(vendaDAO,null);
        this.vendaRepository = vendaRepository;
        this.produtoClient = produtoClient;
    }

    @Cacheable(value = "VendaDTO",cacheManager = "cache")
    public Page<Venda> getAll(Pageable pageable){
        return this.vendaRepository.findAll(pageable);
    }

    public VendaDTO getById(String id){
        return this.vendaDAO.getVendaById(id);
    }

    public ResponseEntity<Boolean> sell(String id){
        return ResponseEntity.ok(this.vendaTransaction.sell(id));
    }

    public List<Venda> listVendas(){
        List<Venda> vendaList = new ArrayList<>();
        vendaRepository.findAll().forEach(vendaList::add);
        vendaList.forEach(System.out::println);
        return vendaList;
    }

    public List<Venda> listByUser(String userId){
        return vendaRepository.findAllByUserId(userId) ;
    }

    public Page<Venda> listByProduto(String produtoId, Pageable pageable){
        return vendaRepository.findAllByProdutos(produtoId, pageable) ;
    }

    public Venda getVendaById(String id){
        Optional<Venda> vendaOptional = vendaRepository.findById(id);
        return vendaOptional.orElseThrow(()->new BadRequestException("Pedido inexistente!"));
    }

    public List<Venda> getVendaFechadaPorUsuario(String userId){
        return vendaRepository.findByUserAndFechado(userId, true);
    }

    public List<Venda> getPedidoPorUsuario(String userId){
        return vendaRepository.findByUserAndFechado(userId, false);
    }

    public Venda updateVenda(Venda venda){
        Venda venda1 = isVendaClosed(venda);
        Venda vendaUpdate = updateVenda(venda1, venda);
        vendaRepository.save(vendaUpdate);
        return vendaUpdate;
    }
    public String deleteVenda(String id){
        Venda venda = getVendaById(id);
        isVendaClosed(venda);
        vendaRepository.delete(venda);
        return venda.getId();
    }

    public Venda createVenda(Venda venda){
        return vendaRepository.save(venda);
    }

    public Venda updateVenda(Venda vendaUpdate, Venda venda){
        vendaUpdate.setProdutoId(venda.getProdutoId());
        return vendaUpdate;
    }

    public Venda isVendaClosed(Venda venda){
        Optional<Venda> vendaOptional = vendaRepository.findById(venda.getId());
        Venda venda1 = vendaOptional.orElseThrow(()->new BadRequestException("Pedido inexistente!"));

        if(venda1.getFechado())
            throw new BadRequestException("Vendas concluidas não podem ser alteradas");
        return venda;
    }

    public Venda compraEfetuada(Venda venda){
        Venda v = getVendaById(venda.getId());
        v.setBuyDate(LocalDate.now());
        v.setFechado(true);
        v.setBuyDate(LocalDate.now());
        return vendaRepository.save(v);
    }

    public List<Venda> getPedidos() {
        return this.vendaRepository.findVendaByFechado(false);
    }
}
