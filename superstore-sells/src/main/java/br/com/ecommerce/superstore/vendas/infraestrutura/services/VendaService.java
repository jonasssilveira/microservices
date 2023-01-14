package br.com.ecommerce.superstore.vendas.infraestrutura.services;

import br.com.ecommerce.superstore.vendas.adapter.ProdutoClientRepository;
import br.com.ecommerce.superstore.vendas.adapter.VendaDAOImpl;
import br.com.ecommerce.superstore.vendas.domain.dto.VendaDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.domain.usecase.VendaTransaction;
import br.com.ecommerce.superstore.vendas.infraestrutura.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    VendaDAOImpl vendaDAO;
    VendaTransaction vendaTransaction;
    VendaDAOImp vendaDAOImp;

    ProdutoClientRepository produtoClient;
    VendaService(@Autowired VendaDAOImpl vendaDAO,
                 @Autowired VendaDAOImp vendaDAOImp,
                 @Autowired ProdutoClientRepository produtoClient){
        this.vendaDAO = vendaDAO;
        this.vendaTransaction = new VendaTransaction(vendaDAO,null);
        this.vendaDAOImp = vendaDAOImp;
        this.produtoClient = produtoClient;
    }

    @Cacheable(value = "VendaDTO",cacheManager = "cache")
    public Page<Venda> listVendas(Pageable pageable){
        return this.vendaDAOImp.findAll(pageable);
    }

    public VendaDTO getById(String id){
        return this.vendaDAO.getVendaById(id);
    }

    public ResponseEntity<Boolean> sell(String id){
        return ResponseEntity.ok(this.vendaTransaction.sell(id));
    }

    public List<VendaDTO> listByUserId(String userId){
        List<Venda> allByUserId = vendaDAOImp.findAllByUserId(userId);
        return allByUserId.stream().map(v -> VendaDTO.vendaTOVendaDTO(v)).collect(Collectors.toList());
    }

    public Venda getVendaById(String id){
        Optional<Venda> vendaOptional = vendaDAOImp.findById(id);
        return vendaOptional.orElseThrow(()->new BadRequestException("Pedido inexistente!"));
    }

    public List<Venda> getVendaFechadaPorUsuario(String userId){
        return vendaDAOImp.findByUserIdAndFechado(userId, true);
    }

    public String deleteVenda(String id){
        Venda venda = getVendaById(id);
        isVendaClosed(venda);
        vendaDAOImp.delete(venda);
        return venda.getId();
    }

    public Venda createVenda(Venda venda){
        return vendaDAOImp.save(venda);
    }

    public Venda isVendaClosed(Venda venda){
        Optional<Venda> vendaOptional = vendaDAOImp.findById(venda.getId());
        Venda venda1 = vendaOptional.orElseThrow(()->new BadRequestException("Pedido inexistente!"));

        if(venda1.getFechado())
            throw new BadRequestException("Vendas concluidas não podem ser alteradas");
        return venda;
    }

    public Venda compraEfetuada(VendaDTO vendaRequest){
        Venda v = getVendaById(vendaRequest.getId());
        v.setBuyDate(LocalDate.now());
        v.setFechado(true);
        v.setBuyDate(LocalDate.now());
        vendaRequest.getProdutoRequests().stream().map(product->produtoClient.sell(product.getProductId(), product.getQuantToSell()));
        return vendaDAOImp.save(v);
    }

    public List<Venda> getPedidos() {
        return this.vendaDAOImp.findVendaByFechado(false);
    }
}
